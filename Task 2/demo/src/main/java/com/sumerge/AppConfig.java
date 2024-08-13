package com.sumerge;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import com.sumerge.task3.*;

@Configuration
@ComponentScan("com.sumerge")
public class AppConfig {

    @Bean
    public CourseService courseService(@Qualifier("advancedRecommenderBean") CourseRecommender courseRecommender){
        return new CourseService(courseRecommender);
    }

    @Bean(name = "basicRecommenderBean")
    @Primary // 1 - Using Primary gives Precedence
    public CourseRecommender basicCoursesRecommender() { return new MidCourses();}

    @Bean(name = "advancedRecommenderBean")
    public CourseRecommender advancedCoursesRecommender() { return new AdvancedCourses();}
}
