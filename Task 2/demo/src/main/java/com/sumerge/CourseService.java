package com.sumerge;

import com.sumerge.task3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.sumerge.task3.DatabaseClasses.Course;


import java.util.List;

public class CourseService {
    private CourseRecommender courseRecommender;

    @Autowired
    CourseRepository courseRepository;

    public CourseService (@Qualifier("basicRecommenderBean") CourseRecommender courseRecommender ){
        this.courseRecommender = courseRecommender;
    }

    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendedCourses();
    }


    public Course getCourseById(int id){
         return courseRepository.getCourseById(id);
    }

    public void deleteCourse(int id){
        courseRepository.deleteCourse(id);
    }

    public Course updateCourse(int id, Course course){
        return courseRepository.updateCourse(id, course);
    }

    public Course addCourse(Course course){
        return courseRepository.addCourse(course);
    }


}
