package com.example.softwareproject.stadium.services;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.softwareproject.stadium.controllers.reportsController;
import com.example.softwareproject.stadium.models.Comments;

@Service
public class CommentsService {
    private RestTemplate restTemplate;
    private  String baseUrl="http://localhost:8081";

    public CommentsService(){
        this.restTemplate=new RestTemplate();
    }

    public void addComment(Comments comments) {
    String url= baseUrl+"/contuctus";
    if(comments.getId()==null)
    {
        this.restTemplate.postForObject(url, comments,Comments.class);
    }else{
        url+="/"+comments.getId();
        HttpEntity<Comments> reqEntity = new HttpEntity<Comments>(comments);
        this.restTemplate.put(url, reqEntity);
    }
    }
}
