package com.example.softwareproject.stadium.controllers;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
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

import com.example.softwareproject.stadium.models.Teams;
import com.example.softwareproject.stadium.services.FileUploadService;
import com.example.softwareproject.stadium.services.TeamsService;

@Controller
@RequestMapping("/team")
public class TeamController {

@Autowired
private FileUploadService fileUploadService;
@Autowired
private TeamsService teamsService;

@GetMapping("/add")
public ModelAndView AddTeams()
{   
    ModelAndView view = new ModelAndView("AddTeam.html");
    Teams team = new Teams();
    view.addObject("Team", team);
    return view;
}

@PostMapping("/add")
public ModelAndView AddTeams(@ModelAttribute Teams teams,@RequestParam("image") MultipartFile multipartFile) throws IOException
{   
    String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    //teams.setImage(filename);
    

    if(teamsService.addTeam(teams) == null)
    {
        ModelAndView teamView = new ModelAndView("AddTeam.html");
        Teams team = new Teams();
        teamView.addObject("Team", team);
        return teamView;
    }
    String upload = "teams/" + teams.getId();
    this.fileUploadService.saveFile(upload, filename, multipartFile);

   ModelAndView homeView = new ModelAndView("home.html");
     return homeView;

}

}
