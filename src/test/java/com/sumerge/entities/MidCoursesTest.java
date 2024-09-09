package com.sumerge.entities;

import com.sumerge.repos.JPACourseRepository;
import com.sumerge.task3.DatabaseClasses.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MidCoursesTest {

    @Mock
    JPACourseRepository jpaCourseRepository;

    @InjectMocks
    MidCourses midCourses;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void recommendedCourses_Successful() {
        List<Course> mockCourses = new ArrayList<>();
        mockCourses.add(new Course("hello1", "Course 1", 9));
        mockCourses.add(new Course("hello", "Course 2", 6));
        when(jpaCourseRepository.findAll()).thenReturn(mockCourses);
        assertEquals(mockCourses, midCourses.recommendedCourses());
    }
}