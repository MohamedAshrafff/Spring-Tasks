package com.sumerge;

import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sumerge.task3.*;

public class AdvancedCourses implements CourseRecommender {

    @Override
    public List<Course> recommendedCourses() {
        return new ArrayList<>(Arrays.asList(
                new Course("MID Courses"),
                new Course("Object Oriented Programming"),
                new Course("Data Analytics 1")
        ));
    }

}
