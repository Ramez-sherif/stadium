package com.example.softwareproject.stadium.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Stores;
import com.example.softwareproject.stadium.services.StoreService;

@Controller
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("add-store")
    public ModelAndView getAddStore(){
        ModelAndView mav = new ModelAndView("addStore.html");
        Stores newStore =new Stores();
        mav.addObject("newStore", newStore);
        return mav;
    }

    @PostMapping("add-store")
    public String postAddStore(@ModelAttribute Stores stores){
        storeService.addStore(stores);
        return "redirect:/stores/get";
    }

    @GetMapping("view-all")
    public ModelAndView getAllStores(){
        ModelAndView mav= new ModelAndView("viewStores.html");
        List<Stores> allStores= storeService.getAllStores();
        mav.addObject("allStores", allStores);
        return mav;
    }
    

}
