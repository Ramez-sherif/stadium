package com.example.softwareproject.stadium.controllers;

import java.io.IOException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.services.CategoryService;
import com.example.softwareproject.stadium.services.FileUploadService;
import com.example.softwareproject.stadium.services.StadiumCategoriesService;
import com.example.softwareproject.stadium.services.StadiumImageService;
import com.example.softwareproject.stadium.services.StadiumService;
//@Controller
@Controller
@RequestMapping("/stadium")
public class StadiumController {
    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StadiumImageService stadiumImageService;
    @Autowired
    private StadiumCategoriesService stadiumCategoriesService;
    @Autowired
    private FileUploadService fileUploadService;
   

    @GetMapping("/add")
    public ModelAndView addStadium(){
        ModelAndView addStadiumView = new ModelAndView("AddStadium.html");
        
        List<StadiumImage> allStadiumImage = this.stadiumImageService.getAllImgUrl();
        List<Category> categories = this.categoryService.getAllCategories();
        Stadium stadium= new Stadium();
        addStadiumView.addObject("allStadiumImages", allStadiumImage)
        .addObject("allCategories", categories)
        .addObject("stadium",stadium );        

        return addStadiumView;
    }
    @PostMapping("/add")
    public ModelAndView addStadium(@ModelAttribute Stadium stadium){
        
       
        StadiumImage stadiumImage = stadium.getStadiumImage();
        System.out.println("SID = "+ stadiumImage.getId());
        if(stadiumImage.getId() != null){           
            List<Category> stadiumCategories = this.categoryService.getCategoriesByStadium(stadiumImage.getId());
            if(stadiumCategories.size() != 0){
                stadium.setStadiumImage(this.stadiumImageService.getStadiumById(stadiumImage.getId()));
                Stadium new_stadium = this.stadiumService.addStadium(stadium);
                if(new_stadium != null){
                    for(Category category : stadiumCategories) {
                        this.stadiumCategoriesService.addCategoryToStadium(new_stadium.getId(), category.getId());
                    }
                    ModelAndView homeView = new ModelAndView("newHome.html");
                    return homeView;
                }
            }
        }
        ModelAndView addStadiumView = new ModelAndView("AddStadium.html");
        List<StadiumImage> allStadiumImage = this.stadiumImageService.getAllImgUrl();
        List<Category> categories = this.categoryService.getAllCategories();
        Stadium stadiums= new Stadium();
        addStadiumView.addObject("allStadiumImages", allStadiumImage)
        .addObject("allCategories", categories)
        .addObject("stadium",stadiums );
        return addStadiumView;         
    }
    @GetMapping("/section")
    public ModelAndView addStadiumImage(){
        ModelAndView addStadiumView = new ModelAndView("AddStadiumImage.html");
        StadiumImage section= new StadiumImage();
        addStadiumView.addObject("section", section);
        return addStadiumView;
    }
    @PostMapping("/section")
    public String addStadiumImage(@ModelAttribute StadiumImage stadiumImage,@RequestParam("images") MultipartFile multipartFile) throws IOException{
        
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        stadiumImage.setImgUrl(filename);
        if(stadiumImageService.addStadiumImage(stadiumImage) == null)  return "redirect:/stadium/section";
        
        String upload = "stadiums/" + stadiumImage.getId();
        this.fileUploadService.saveFile(upload, filename, multipartFile);

        return "redirect:/Home";
    }
}
