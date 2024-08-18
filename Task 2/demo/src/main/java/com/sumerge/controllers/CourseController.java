package com.sumerge.controllers;

import com.sumerge.services.CourseService;
import com.sumerge.task3.DatabaseClasses.Course;
import com.sumerge.task3.DatabaseClasses.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/view")
    public ResponseEntity<List<Course>> viewAllCourses() {
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
        Course newCourse ;
        if(courseService.getCourseById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            newCourse = courseService.updateCourse(id, course);
            return ResponseEntity.ok(newCourse);
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        if(courseService.getCourseById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            courseService.deleteCourse(id);
            return ResponseEntity.ok("Deleted course Successfully");
        }
    }

    @GetMapping("courseDTO/{id}")
    public ResponseEntity<CourseDTO> courseDTO(@PathVariable int id) {
        CourseDTO newDTO = courseService.getCourseDTOById(id);
        System.out.println(newDTO.toString());
        return ResponseEntity.ok(newDTO);
    }

    @GetMapping("/paginated")
    public List<Course> getCourses(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size) {
        return courseService.getPaginatedCourses(page, size);
    }

}
