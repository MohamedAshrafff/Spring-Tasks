package com.sumerge.entities;

import com.sumerge.task3.CourseRecommender;
import com.sumerge.task3.DatabaseClasses.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedCoursesTest {

    CourseRecommender courseRecommender = new AdvancedCourses();
    @Test
    void recommendedCourses_SuccsessfulTest() {
        ArrayList<Course> expectedCourses = new ArrayList<>(Arrays.asList(
                new Course("MID Courses"),
                new Course("Object Oriented Programming"),
                new Course("Data Analytics 1")));

        List<Course> recommendedCourses = courseRecommender.recommendedCourses();
        assertEquals(expectedCourses.size(), recommendedCourses.size());
    }
}