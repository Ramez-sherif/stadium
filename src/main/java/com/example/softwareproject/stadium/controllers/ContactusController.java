package com.example.softwareproject.stadium.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Comments;
import com.example.softwareproject.stadium.services.CommentsService;

@Controller
@RequestMapping("/contactus")
public class ContactusController {
    @Autowired
    private CommentsService commentsService;

    @GetMapping("add")
    public ModelAndView getAddComment(){
    ModelAndView view = new ModelAndView("ContactUs.html");
    Comments comments = new Comments();
    view.addObject("comments", comments);
    return view;
    }

    @PostMapping("")
    public String  addcomment(@ModelAttribute Comments comments){
        this.commentsService.addComment(comments);
        return "redirect:/Home";
    }
   /* @GetMapping("")
    public ModelAndView  getAllComments(){
        ModelAndView view = new ModelAndView("ViewContactUs.html");
        List<Comments> allComments=commentsService.getAllComments();
       view.addObject("allComments", allComments);
       return view;
    } */
}
