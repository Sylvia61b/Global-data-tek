package com.example.controller;

import com.example.repository.EnrolledCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.example.entity.EnrolledCourse;

@RestController
@RequestMapping("/enrolledCourses")
public class EnrolledCourseController {
    @Autowired
    private EnrolledCourseRepository repository;

    @GetMapping("/user/{userId}")
    public List<Long> getCoursesByUserId(@PathVariable Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(EnrolledCourse::getCourseId)
                .collect(Collectors.toList());
    }
}
