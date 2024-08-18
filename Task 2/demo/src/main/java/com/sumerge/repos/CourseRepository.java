package com.sumerge.repos;

import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //CHECKER
    public int getCoursesCount(){
        String sql = "select count(*) from course";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //TODO 1
    public Course addCourse(Course course){
        System.out.println("Courses Count : "+getCoursesCount());
        String sql = "INSERT INTO Course (course_name, course_description, course_credit, author_id)" +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                course.getCourse_name() ,
                course.getCourse_description() ,
                course.getCourse_credit() , 1);
        System.out.println("____ADDED COURSE____");
        System.out.println("Courses Count : "+getCoursesCount());
        return course;
    }


    //TODO 2
    public Course updateCourse(int id, Course course){
        String sql = "UPDATE Course SET course_name = ? ,course_description = ? , course_credit = ? WHERE course_id = ?";

        jdbcTemplate.update(sql,
                course.getCourse_name() ,
                course.getCourse_description() ,
                course.getCourse_credit() , id);
        System.out.println("____UPDATED SUCCESSFULLY____");
        getCourseById(id);
        return course;
    }

    //TODO 3
    public Course getCourseById(int id){
        Course course;
        String sql = "select * from course where course_id = ?";
        course = jdbcTemplate.queryForObject(sql,(rs, rowNum) ->
                new Course(
                        rs.getString("course_name"),
                        rs.getString("course_description"),
                        rs.getInt("author_id"),
                        rs.getInt("course_credit")
                ),id);
        System.out.println("Course Got from DB is :");
        System.out.println("Name : "+ course.getCourse_name());
        System.out.println("Description : " + course.getCourse_description());
        System.out.println("Course Credit : " + course.getCourse_credit());
        System.out.println("Author ID : "+ course.getAuthor_id());
        return course;
    }

    //TODO 4
    public void deleteCourse(int id){
        System.out.println("Courses Count : "+getCoursesCount());
        String sql = "DELETE FROM Course WHERE course_id = ?";
        jdbcTemplate.update(sql,id);
        System.out.println(getCoursesCount());
        System.out.println("Courses Count : "+getCoursesCount());
    }

    //TODO Recommended Bean
    public List<Course> getAllCourses(){
        String sql = "select * from course where course_credit >= 5";
        return jdbcTemplate.query(sql ,
                (rs , rowNum) -> new Course(
                    rs.getString("course_name"),
                    rs.getString("course_description"),
                    rs.getInt("author_id") ,
                    rs.getInt("course_credit")));
        }
}
