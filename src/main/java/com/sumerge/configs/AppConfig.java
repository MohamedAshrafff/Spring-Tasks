package com.sumerge.configs;

import com.sumerge.AdvancedCourses;
import com.sumerge.MidCourses;
import com.sumerge.mappers.CourseMapper;
import com.sumerge.services.CourseService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import com.sumerge.task3.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@ComponentScan("com.sumerge")
public class AppConfig {

    @Bean
    @Primary
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Course_Management");
        dataSource.setUsername("postgres");
        dataSource.setPassword("12345");
        return dataSource;
    }

    @Bean
    public CourseService courseService(@Qualifier("basicRecommenderBean") CourseRecommender courseRecommender){
        return new CourseService(courseRecommender);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource()) ;
    };

    @Bean
    public CourseMapper courseMapper() {
        return Mappers.getMapper(CourseMapper.class);
    }


    @Bean(name = "basicRecommenderBean")
    public CourseRecommender basicCoursesRecommender() { return new MidCourses();}

    @Bean(name = "advancedRecommenderBean")
    public CourseRecommender advancedCoursesRecommender() { return new AdvancedCourses();}
}
