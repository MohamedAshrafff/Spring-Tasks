package com.sumerge;

import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


}
