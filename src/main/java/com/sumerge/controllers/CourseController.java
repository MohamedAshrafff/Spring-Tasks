package com.sumerge.controllers;

import com.sumerge.exceptions.NotFoundException;
import com.sumerge.services.CourseService;
import com.sumerge.task3.DatabaseClasses.Author;
import com.sumerge.task3.DatabaseClasses.Course;
import com.sumerge.task3.DTOs.CourseDTO;
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
    public ResponseEntity<List<CourseDTO>> getRecommendedCourses() {
       return ResponseEntity.ok(courseService.getRecommendedCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> viewCourseById(@PathVariable int id) {
        return ResponseEntity.ok(courseService.getCourseByIdDTO(id));
    }


    @PostMapping("/add")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody Course course) {
        CourseDTO newCourseDTO = courseService.addCourse(course);
        return new ResponseEntity<>(newCourseDTO , HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable int id, @RequestBody Course course) {;
        CourseDTO newCourseDTO = courseService.updateCourse(id, course);
        return ResponseEntity.ok(newCourseDTO);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
            return ResponseEntity.ok(courseService.deleteCourse(id));
    }

    @PutMapping("/{courseId}/author/{authorId}")
    public ResponseEntity<CourseDTO> AssignAuthorToCourse(@PathVariable int courseId, @PathVariable int authorId) {
        CourseDTO courseDTO = courseService.addAuthorToCourse(courseId , authorId);
        return ResponseEntity.ok(courseDTO);
    }

    @PutMapping("/{courseId}/assessment/{assessmentId}")
    public ResponseEntity<CourseDTO> AssignAssessmentToCourse(@PathVariable int courseId, @PathVariable int assessmentId) {
        CourseDTO courseDTO = courseService.addAssessmentToCourse(courseId , assessmentId);
        return ResponseEntity.ok(courseDTO);
    }

    @PutMapping("/{courseId}/rating/{ratingId}")
    public ResponseEntity<CourseDTO> AssignRatingToCourse(@PathVariable int courseId, @PathVariable int ratingId) {
        CourseDTO courseDTO = courseService.addRatingToCourse(courseId , ratingId);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/courseDTO/{id}")
    public ResponseEntity<CourseDTO> courseDTO(@PathVariable int id) {
        CourseDTO newDTO = courseService.getCourseDTOById(id);
        return ResponseEntity.ok(newDTO);
    }

    @GetMapping("/paginated")
    public List<CourseDTO> getCourses(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) {
        return courseService.getPaginatedCourses(page, size);
    }

}
