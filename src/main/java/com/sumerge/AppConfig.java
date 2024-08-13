package com.sumerge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.sumerge")
public class AppConfig {

    @Bean(name = "basicRecommenderBean")
     // 1 - Using Primary gives Precedence
    @Primary
    public CourseRecommender basicCoursesRecommender() { return new BasicCourses();}

    @Bean(name = "advancedRecommenderBean")
    public CourseRecommender advancedCoursesRecommender() { return new AdvancedCourses();}
}
