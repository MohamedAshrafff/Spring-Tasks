package com.sumerge;

import com.sumerge.task3.*;
import com.sumerge.task3.DatabaseClasses.Course;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//METHOD 1 of Overriding
public class MidCourses implements CourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return new  ArrayList<>(Arrays.asList(
                new Course("MID Courses"),
                new Course("Object Oriented Programming"),
                new Course("Data Analytics 1")
        ));
    }
}
