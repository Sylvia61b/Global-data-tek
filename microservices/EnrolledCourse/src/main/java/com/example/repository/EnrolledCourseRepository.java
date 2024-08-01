package com.example.repository;

import com.example.entity.EnrolledCourse;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface EnrolledCourseRepository extends JpaRepository<EnrolledCourse, Long> {

    List<EnrolledCourse> findByUserId(Long userId);
}
