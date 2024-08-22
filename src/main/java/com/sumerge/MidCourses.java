package com.sumerge;

import com.sumerge.repos.JPACourseRepository;
import com.sumerge.task3.*;
import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//METHOD 1 of Overriding
public class MidCourses implements CourseRecommender {
    @Autowired
    JPACourseRepository jpaCourseRepository;

    @Override
    public List<Course> recommendedCourses() {
        return jpaCourseRepository.findAll();
    }
}
