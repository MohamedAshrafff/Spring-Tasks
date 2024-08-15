package com.sumerge;

import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.sumerge.task3.*;

public class AdvancedCourses implements CourseRecommender {

    @Autowired
    CourseRepository courseRepo;
    @Override
    public List<Course> recommendedCourses() {
        return courseRepo.getAllCourses();
    }

}
