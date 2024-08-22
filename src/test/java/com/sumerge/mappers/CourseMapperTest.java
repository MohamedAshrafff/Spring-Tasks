package com.sumerge.mappers;

import com.sumerge.task3.DatabaseClasses.Course;
import com.sumerge.task3.DatabaseClasses.CourseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperTest {

    private CourseMapper courseMapper = new CourseMapperImpl();

    @Test
    void courseToCourseDTO() {
        Course course = new Course();
        course.setCourse_id(1);
        course.setCourse_name("Introduction to Programming");
        course.setCourse_description("Learn the basics of programming.");

        CourseDTO courseDTO = courseMapper.courseToCourseDTO(course);

        assertNotNull(courseDTO);
        assertEquals(1, courseDTO.getCourse_id());
        assertEquals("Introduction to Programming", courseDTO.getCourse_name());
        assertEquals("Learn the basics of programming.", courseDTO.getCourse_description());
    }

    @Test
    void courseToCourseDTO_NullCourse_ShouldReturnNull() {
        CourseDTO courseDTO = courseMapper.courseToCourseDTO(null);
        assertNull(courseDTO);
    }
}