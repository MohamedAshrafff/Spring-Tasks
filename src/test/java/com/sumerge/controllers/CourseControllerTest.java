package com.sumerge.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sumerge.exceptions.AlreadyExistException;
import com.sumerge.exceptions.GlobalExceptionHandler;
import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.CourseMapper;
import com.sumerge.services.CourseService;

import com.sumerge.task3.DatabaseClasses.Course;
import com.sumerge.task3.DTOs.CourseDTO;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@WebMvcTest(CourseController.class)
@ContextConfiguration(classes =CourseController.class)
@Import(GlobalExceptionHandler.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    private Course course;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        course = new Course();
        course.setCourse_name("new course");
        course.setCourse_credit(7);

    }

    @Test
    void viewCourseById_Successful()throws Exception {
        when(courseService.getCourseByIdDTO(1)).thenReturn(new CourseDTO());
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk());
    }

    @Test
    void viewCourseById_NotSuccessful() throws Exception {
        when(courseService.getCourseByIdDTO(1))
                .thenThrow(new NotFoundException("course with id 1 not found"));

        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("course with id 1 not found"));
    }

    @Test
    void getRecommendedCourses_Successful() throws Exception {
        List<CourseDTO> recommendedCourses = new ArrayList<>();
        recommendedCourses.add(new CourseDTO());
        recommendedCourses.add(new CourseDTO());

        when(courseService.getRecommendedCourses()).thenReturn(recommendedCourses);
        mockMvc.perform(get("/api/courses/recommended"))
                .andExpect(status().isOk());
    }

    @Test
    void getRecommendedCourses_NotSuccessful() throws Exception {
        when(courseService.getRecommendedCourses()).thenThrow(new NotFoundException("no courses with this criteria"));
        mockMvc.perform(get("/api/courses/recommended"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addCourse_Successful() throws Exception {
        when(courseService.addCourse(course)).thenReturn(new CourseDTO());
        mockMvc.perform(post("/api/courses/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCourse_Successful() throws Exception {
        when(courseService.getCourseById(1)).thenReturn(course);
        when(courseService.updateCourse(1,course)).thenReturn(new CourseDTO());
        mockMvc.perform(put("/api/courses/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk());
    }
    @Test
    void updateCourse_NotSuccessful() throws Exception {
        when(courseService.updateCourse(eq(1), any(Course.class))).thenThrow(new NotFoundException("course with id 1 not found"));

        mockMvc.perform(put("/api/courses/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCourse_Successful() throws Exception {
        when(courseService.deleteCourse(1)).thenReturn("Deleted course Successfully");
        mockMvc.perform(delete("/api/courses/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted course Successfully"));
    }

    @Test
    void deleteCourse_NotSuccessful() throws Exception {
        when(courseService.deleteCourse(1)).thenThrow(new NotFoundException("Course with id 1 not found"));
        mockMvc.perform(delete("/api/courses/delete/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Course with id 1 not found"));
    }

    @Test
    void assignAuthorToCourse_Successful() throws Exception{
      when(courseService.addAuthorToCourse(1,1)).thenReturn(new CourseDTO() );
      mockMvc.perform(put("/api/courses/1/author/1"))
              .andExpect(status().isOk());
    }

    @Test
    void assignAuthorToCourse_NotSuccessful() throws Exception{
        when(courseService.addAuthorToCourse(1,1)).thenThrow(new AlreadyExistException("This Author already exist !"));
        mockMvc.perform(put("/api/courses/1/author/1"))
                .andExpect(status().isConflict());
    }

    @Test
    void assignAssessmentToCourse_Successful() throws Exception{
        when(courseService.addAssessmentToCourse(1,1)).thenReturn(new CourseDTO());
        mockMvc.perform(put("/api/courses/1/assessment/1"))
                .andExpect(status().isOk());
    }

    @Test
    void assignAssessmentToCourse_NotSuccessful() throws Exception{
        when(courseService.addAssessmentToCourse(1,1)).thenThrow(new AlreadyExistException("There is Already an assessment for this course !"));
        mockMvc.perform(put("/api/courses/1/assessment/1"))
                .andExpect(status().isConflict());
    }

    @Test
    void assignRatingToCourse_Successful() throws Exception{
        when(courseService.addRatingToCourse(1,1)).thenReturn(new CourseDTO());
        mockMvc.perform(put("/api/courses/1/rating/1"))
                .andExpect(status().isOk());
    }

    @Test
    void assignRatingToCourse_NotSuccessful() throws Exception{
        when(courseService.addRatingToCourse(1,1)).thenThrow(new AlreadyExistException("There is Already a rating for this course !"));
        mockMvc.perform(put("/api/courses/1/rating/1"))
                .andExpect(status().isConflict());
    }

    @Test
    void courseDTO_Successful() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        when(courseService.getCourseDTOById(1)).thenReturn(courseDTO);
        mockMvc.perform(get("/api/courses/courseDTO/1"))
                .andExpect(status().isOk());
    }

    @Test
    void courseDTO_NotSuccessful() throws Exception {
        when(courseService.getCourseDTOById(1)).thenThrow(new NotFoundException("No course with this id found !"));
        mockMvc.perform(get("/api/courses/courseDTO/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCoursesPaginated_Successful() throws Exception {
        List<CourseDTO> courses = new ArrayList<>();
        courses.add(new CourseDTO());
        courses.add(new CourseDTO());
        when(courseService.getPaginatedCourses(0,5)).thenReturn(courses);
        mockMvc.perform(get("/api/courses/paginated"))
                .andExpect(status().isOk());
    }

    @Test
    void getCoursesPaginated_NotSuccessful() throws Exception {
        when(courseService.getPaginatedCourses(0,5)).thenThrow(new NotFoundException("No courses found"));
        mockMvc.perform(get("/api/courses/paginated"))
                .andExpect(status().isNotFound());
    }

}