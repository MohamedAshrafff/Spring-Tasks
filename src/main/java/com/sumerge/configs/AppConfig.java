package com.sumerge.configs;

import com.sumerge.entities.AdvancedCourses;
import com.sumerge.entities.MidCourses;
import com.sumerge.services.CourseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import com.sumerge.task3.*;


@Configuration
@ComponentScan("com.sumerge")
public class AppConfig {

    @Bean
    public CourseService courseService(@Qualifier("basicRecommenderBean") CourseRecommender courseRecommender){
        return new CourseService(courseRecommender);
    }

    @Bean(name = "basicRecommenderBean")
    public CourseRecommender basicCoursesRecommender() { return new MidCourses();}

    @Bean(name = "advancedRecommenderBean")
    public CourseRecommender advancedCoursesRecommender() { return new AdvancedCourses();}
}
