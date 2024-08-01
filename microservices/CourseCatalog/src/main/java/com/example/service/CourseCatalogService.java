package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.CourseRatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseCatalogService {
    @Autowired
    private RestTemplate restTemplate;

    public List<CourseDTO> getCourseDTOsByUserId(Long userId) {
        List<Long> courseIds = Optional.ofNullable(
                restTemplate.getForObject("http://EnrolledCourse/enrolledCourses/user/" + userId, List.class)
        ).orElse(new ArrayList<>());

        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Long courseId : courseIds) {
            List<CourseRatingDTO> ratings = Optional.ofNullable(
                    restTemplate.getForObject("http://Course-Rating/courseRatings/course/" + courseId, List.class)
            ).orElse(new ArrayList<>());
            courseDTOs.add(new CourseDTO(courseId, ratings));
        }
        return courseDTOs;
    }
}
