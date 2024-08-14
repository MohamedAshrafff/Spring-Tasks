package com.sumerge;

import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sumerge.task3.*;

public class AdvancedCourses implements CourseRecommender {

    @Override
    public List<Course> recommendedCourses() {
        return new ArrayList<>(
                Arrays.asList(
                        new Course("Advanced Courses"),
                        new Course("Operating Systems") ,
                        new Course("Algorithms Paradigm")));
    }
}
