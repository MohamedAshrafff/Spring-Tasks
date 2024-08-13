package com.sumerge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CourseRecommender courseRecommender;

    //  3 - By Variable Name -> if no name defined in @Bean then MethodName else Bean name
    //@Autowired
    //private CourseRecommender advancedCoursesRecommender;


    //  2 - Using Qualifier
    @Autowired
    public CourseService (@Qualifier("basicRecommenderBean") CourseRecommender courseRecommender){
        this.courseRecommender = courseRecommender;
    }

    @Autowired
    public void setCourseRecommender(@Qualifier("advancedRecommenderBean") CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendedCourses();
    }
}
