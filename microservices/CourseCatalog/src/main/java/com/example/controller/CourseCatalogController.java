package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.service.CourseCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseCatalog")
public class CourseCatalogController {
    @Autowired
    private CourseCatalogService service;

    @GetMapping("/user/{userId}")
    public List<CourseDTO> getCourseDetails(@PathVariable Long userId) {
        return service.getCourseDTOsByUserId(userId);
    }
}