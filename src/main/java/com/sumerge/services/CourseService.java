package com.sumerge.services;

import com.sumerge.exceptions.AlreadyExistException;
import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.CourseMapper;
import com.sumerge.repos.JPAAssessmentRepository;
import com.sumerge.repos.JPAAuthorRepository;
import com.sumerge.repos.JPACourseRepository;
import com.sumerge.repos.JPARatingRepository;
import com.sumerge.task3.CourseRecommender;
import com.sumerge.task3.DTOs.CourseDTO;
import com.sumerge.task3.DatabaseClasses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CourseService {

    CourseRecommender courseRecommender;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    JPACourseRepository jpaCourseRepository;

    @Autowired
    JPAAssessmentRepository jpaAssessmentRepository;

    @Autowired
    JPAAuthorRepository jpaAuthorRepository;

    @Autowired
    JPARatingRepository jpaRatingRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    RatingService ratingService;
    @Autowired
    AssessmentService assessmentService;


    public CourseService (@Qualifier("basicRecommenderBean") CourseRecommender courseRecommender){
        this.courseRecommender = courseRecommender;
    }

    public List<CourseDTO> getRecommendedCourses() {
        return courseRecommender.recommendedCourses().stream().map(
                course -> courseMapper.courseToCourseDTO(course)
        ).collect(Collectors.toList());
    }


    public Course getCourseById(int id){
         Optional<Course> newCourse = jpaCourseRepository.findById(id);
         if(!newCourse.isPresent()){
            throw new NotFoundException("Course Id of "+id+" not found");
         }
         return newCourse.get();
    }

    public CourseDTO getCourseByIdDTO(int id){
        Optional<Course> newCourse = jpaCourseRepository.findById(id);
        if(!newCourse.isPresent()){
            throw new NotFoundException("Course Id of "+id+" not found");
        }
        return courseMapper.courseToCourseDTO(newCourse.get());
    }

    public String deleteCourse(int id){
        getCourseById(id);
        jpaCourseRepository.deleteById(id);
        return "Deleted course successfully";
    }

    public CourseDTO updateCourse(int id, Course course) {
        getCourseById(id);
        return courseMapper.courseToCourseDTO(jpaCourseRepository.save(course));
    }


    public CourseDTO addCourse(Course course){
        if(jpaCourseRepository.findById(course.getCourse_id()).isPresent()){
            throw new AlreadyExistException("Course Id of "+course.getCourse_id()+" already exist");
        }

        if (course.getCourse_name() == null || course.getCourse_name().trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty or null");
        }
        if (course.getCourse_description() == null || course.getCourse_description().trim().isEmpty()) {
            throw new IllegalArgumentException("Course description cannot be empty or null");
        }
        return courseMapper.courseToCourseDTO(jpaCourseRepository.save(course));
    }

    public CourseDTO getCourseDTOById(int id){
        Course newCourse = getCourseById(id);
        return courseMapper.courseToCourseDTO(newCourse);
    }

    public List<CourseDTO> getPaginatedCourses(int page, int size) {
        if(page<0){
            throw new IllegalArgumentException("Page number cannot be less than zero");
        }
        if(size<=0){
            throw new IllegalArgumentException("Size cannot be less than zero to be able to see responses");
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> pageCourses =  jpaCourseRepository.findAll(pageable);

        Page<CourseDTO> pageCourseDTOs = pageCourses.map(course -> {
            return courseMapper.courseToCourseDTO(course);
        });

        return pageCourseDTOs.getContent();
    }

    public CourseDTO addAuthorToCourse(int courseId, int authorId){
        Course course = getCourseById(courseId);
        Author author = authorService.getAuthorById(authorId);
        if(course.getAuthors().contains(author)){
            throw new AlreadyExistException("Author of id "+authorId+" already exist in course "+courseId);
        }
        course.getAuthors().add(author);
        jpaAuthorRepository.save(author);
        return courseMapper.courseToCourseDTO(jpaCourseRepository.save(course));
    }

    public CourseDTO addRatingToCourse(int courseId, int ratingId){
        Course course = getCourseById(courseId);
        Rating rating = ratingService.getRatingById(ratingId);
        rating.setCourse(course);
        if(course.getRatings().contains(rating)){
            throw new AlreadyExistException("Rating of id "+ratingId+" already exist in course "+courseId);
        }
        course.getRatings().add(rating);
        jpaRatingRepository.save(rating);
        return courseMapper.courseToCourseDTO(jpaCourseRepository.save(course));
    }

    public CourseDTO addAssessmentToCourse(int courseId, int assessmentId){
        Course course = getCourseById(courseId);
        Assessment assessment = assessmentService.getAssessmentById(assessmentId);
        if (course.getAssessment() == assessment || course.getAssessment() != null) {
            throw new AlreadyExistException("Assessment Already Exist for Course ID "+assessmentId);
        }
        course.setAssessment(assessment);
        jpaAssessmentRepository.save(assessment);
        return courseMapper.courseToCourseDTO(jpaCourseRepository.save(course));
    }

}
