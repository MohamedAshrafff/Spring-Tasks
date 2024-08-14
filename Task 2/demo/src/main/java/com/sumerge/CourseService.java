package com.sumerge;

import com.sumerge.task3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.sumerge.task3.DatabaseClasses.Course;


import java.util.List;

@Service
public class CourseService {
    private CourseRecommender courseRecommender;

    JdbcTemplate jdbcTemplate;

    //  2 - Using Qualifier
    public CourseService (@Qualifier("basicRecommenderBean") CourseRecommender courseRecommender , JdbcTemplate jdbcTemplate){
        this.courseRecommender = courseRecommender;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendedCourses();
    }

    //CHECKER
    public int getCoursesCount(){
        String sql = "select count(*) from course";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //TODO 1
    public void addCourse(Course course){
        System.out.println("Courses Count : "+getCoursesCount());
        String sql = "INSERT INTO Course (course_name, course_description, course_credit, author_id)" +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                course.getCourseName() ,
                course.getCourseDescription() ,
                course.getCourseCredit() , 1);
        System.out.println("ADDED COURSE");
        System.out.println("Courses Count : "+getCoursesCount());
    }


    //TODO 2
    public void updateCourse(int id, Course course){
        String sql = "UPDATE Course SET course_name = ? ,course_description = ? , course_credit = ? WHERE course_id = ?";

        jdbcTemplate.update(sql,
                course.getCourseName() ,
                course.getCourseDescription() ,
                course.getCourseCredit() , id);
        System.out.println("UPDATED SUCCESSFULLY");
    }

    //TODO 3
    public void getCourseById(int id){
        Course course;
        String sql = "select * from course where course_id = ?";
        course = jdbcTemplate.queryForObject(sql,(rs, rowNum) ->
                    new Course(
                            rs.getString("course_name"),
                            rs.getString("course_description"),
                            rs.getInt("course_credit"),
                            rs.getInt("author_id")
                    ),id);

        System.out.println("Course Got from DB is :");
        System.out.println("Name : "+ course.getCourseName());
        System.out.println("Description : " + course.getCourseDescription());
        System.out.println("Course Credit : " + course.getCourseCredit());
        System.out.println("Author ID : "+ course.getAuthor_id());
    }

    //TODO 4
    public void deleteCourse(int id){
        System.out.println("Courses Count : "+getCoursesCount());
        String sql = "DELETE FROM Course WHERE course_id = ?";
        jdbcTemplate.update(sql,id);
        System.out.println(getCoursesCount());
        System.out.println("Courses Count : "+getCoursesCount());
    }

}
