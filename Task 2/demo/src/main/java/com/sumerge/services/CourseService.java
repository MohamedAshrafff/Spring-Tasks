package com.sumerge.services;

import com.sumerge.CourseMapper;
import com.sumerge.repos.CourseRepository;
import com.sumerge.repos.JPACourseRepository;
import com.sumerge.task3.*;
import com.sumerge.task3.DatabaseClasses.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;


public class CourseService {
    private CourseRecommender courseRecommender;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    JPACourseRepository jpaCourseRepository;

    public CourseService (@Qualifier("basicRecommenderBean") CourseRecommender courseRecommender ){
        this.courseRecommender = courseRecommender;
    }

    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendedCourses();
    }


    public Course getCourseById(int id){
         Optional<Course> newCourse = jpaCourseRepository.findById(id);
         return newCourse.orElse(null);
    }

    public void deleteCourse(int id){
        jpaCourseRepository.deleteById(id);
    }

    public Course updateCourse(int id, Course course){
        Optional<Course> newCourse = jpaCourseRepository.findById(id);
        return jpaCourseRepository.save(newCourse.orElse(course));
    }

    public Course addCourse(Course course){
        return jpaCourseRepository.save(course);
    }

    public CourseDTO getCourseDTOById(int id){
        Course newCourse = jpaCourseRepository.findById(id).orElse(null);
        System.out.println();
        System.out.println(courseMapper.courseToCourseDTO(newCourse));
        return courseMapper.courseToCourseDTO(newCourse);
    }

    public List<Course> getPaginatedCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> pageCourse =  jpaCourseRepository.findAll(pageable);
        return pageCourse.getContent();
    }


}
