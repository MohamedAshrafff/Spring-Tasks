package com.sumerge.task3;


import com.sumerge.task3.DatabaseClasses.Course;

import java.util.List;

public interface CourseRecommender {
    List<Course> recommendedCourses();
}
