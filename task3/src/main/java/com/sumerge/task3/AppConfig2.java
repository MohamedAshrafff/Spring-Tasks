package com.sumerge.task3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig2 {
    @Bean(name = "advancedRecommenderBean")
    @Primary
    public CourseRecommender advancedCoursesRecommender() { return new BasicCourses();}

}
