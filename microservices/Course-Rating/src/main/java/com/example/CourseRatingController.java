package com.example;

import com.example.entity.CourseRating;
import com.example.repository.CourseRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseRatings")
public class CourseRatingController {
    @Autowired
    private CourseRatingRepository repository;

    @GetMapping("/course/{courseId}")
    public List<CourseRating> getRatingsByCourseId(@PathVariable Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
