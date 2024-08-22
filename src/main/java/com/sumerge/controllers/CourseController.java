package com.sumerge.controllers;

import com.sumerge.services.CourseService;
import com.sumerge.task3.DatabaseClasses.Author;
import com.sumerge.task3.DatabaseClasses.Course;
import com.sumerge.task3.DatabaseClasses.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/recommended")
    public ResponseEntity<List<Course>> getRecommendedCourses() {
       return ResponseEntity.ok(courseService.getRecommendedCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> viewCourseById(@PathVariable int id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }


    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.addCourse(course);
        return new ResponseEntity<>(newCourse , HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course course) {
        Course newCourse = courseService.getCourseById(id);
        newCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(newCourse);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
            return ResponseEntity.ok(courseService.deleteCourse(id));
    }

    @PutMapping("/{courseId}/author/{authorId}")
    public ResponseEntity<Course> AssignAuthorToCourse(@PathVariable int courseId, @PathVariable int authorId) {
        Course course = courseService.addAuthorToCourse(courseId , authorId);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{courseId}/assessment/{assessmentId}")
    public ResponseEntity<Course> AssignAssessmentToCourse(@PathVariable int courseId, @PathVariable int assessmentId) {
        Course course = courseService.addAssessmentToCourse(courseId , assessmentId);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{courseId}/rating/{ratingId}")
    public ResponseEntity<Course> AssignRatingToCourse(@PathVariable int courseId, @PathVariable int ratingId) {
        Course course = courseService.addRatingToCourse(courseId , ratingId);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/courseDTO/{id}")
    public ResponseEntity<CourseDTO> courseDTO(@PathVariable int id) {
        CourseDTO newDTO = courseService.getCourseDTOById(id);
        return ResponseEntity.ok(newDTO);
    }

    @GetMapping("/paginated")
    public List<Course> getCourses(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) {
        return courseService.getPaginatedCourses(page, size);
    }

}
