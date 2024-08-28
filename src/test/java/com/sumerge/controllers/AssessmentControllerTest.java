package com.sumerge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.exceptions.NotFoundException;
import com.sumerge.repos.JPAAssessmentRepository;
import com.sumerge.services.AssessmentService;
import com.sumerge.task3.DatabaseClasses.Assessment;
import com.sumerge.task3.DTOs.AssessmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;


@WebMvcTest(controllers = AssessmentController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes =AssessmentController.class)
@ComponentScan("com.sumerge.exceptions")
class AssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssessmentService assessmentService;

    @MockBean
    private JPAAssessmentRepository jpaAssessmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Assessment assessment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assessment = new Assessment();
        assessment.setAssessment_content("assessment_content");
    }


    @Test
    void getAllAssessments_Successful() throws Exception {
        List<AssessmentDTO> assessments = new ArrayList<>();
        AssessmentDTO assessment1 = new AssessmentDTO();
        assessments.add(assessment1);
        when(assessmentService.getAllAssessments()).thenReturn(assessments);
        mockMvc.perform(get("/api/assessments/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAssessments_NotSuccessful() throws Exception {
        when(assessmentService.getAllAssessments()).thenThrow(new NotFoundException("NO Assessments FOUND"));
        mockMvc.perform(get("/api/assessments/all"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAssessmentById_Successful() throws Exception {
        when(assessmentService.getAssessmentByIdDto(1)).thenReturn(new AssessmentDTO());
        mockMvc.perform(get("/api/assessments/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAssessmentById_NotSuccessful() throws Exception {
        when(assessmentService.getAssessmentByIdDto(eq(1))).thenThrow(new NotFoundException("NO Assessment FOUND"));
        mockMvc.perform(get("/api/assessments/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("NO Assessment FOUND"));
    }

    @Test
    void addAssessment_Successful() throws Exception {
        when(assessmentService.addAssessment(assessment)).thenReturn(new AssessmentDTO());
        mockMvc.perform(post("/api/assessments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(assessment)))
                .andExpect(status().isOk());
    }

    @Test
    void addAssessment_NotSuccessful() throws Exception {
        when(assessmentService.addAssessment(assessment)).thenThrow(new IllegalArgumentException("Assessment content can't be empty or null"));
        mockMvc.perform(post("/api/assessments/add"))
                .andExpect(status().isBadRequest());
   }



}