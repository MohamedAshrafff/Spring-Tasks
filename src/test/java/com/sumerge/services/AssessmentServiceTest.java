package com.sumerge.services;


import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.AssessmentMapper;
import com.sumerge.repos.JPAAssessmentRepository;
import com.sumerge.task3.DatabaseClasses.Assessment;
import com.sumerge.task3.DTOs.AssessmentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {


    @Mock
    private JPAAssessmentRepository jpaAssessmentRepository;

    @Mock
    AssessmentMapper assessmentMapper;

    @InjectMocks
    private AssessmentService assessmentService;

    @Test
    void getAssessmentById_Successful() {
        Assessment assessment = new Assessment();
        when(jpaAssessmentRepository.findById(1)).thenReturn(Optional.of(assessment));
        Assessment assessmentFound = assessmentService.getAssessmentById(1);
        assertEquals(assessment , assessmentFound);
    }

    @Test
    void getAssessmentById_NotSuccessful() {
        when(jpaAssessmentRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> assessmentService.getAssessmentById(1));
    }

    @Test
    void addAssessment_Successful() {
        Assessment assessment = new Assessment();
        assessment.setAssessment_content("full");
       when(assessmentService.addAssessment(assessment)).thenReturn(new AssessmentDTO());
       assertDoesNotThrow(() -> assessmentService.addAssessment(assessment));
       verify(jpaAssessmentRepository, atLeastOnce()).save(any(Assessment.class));
    }

    @Test
    void addAssessment_NotSuccessful() {
        Assessment assessment = new Assessment();
        assertThrows(IllegalArgumentException.class ,() -> assessmentService.addAssessment(assessment));
        verify(jpaAssessmentRepository, times(0)).save(any(Assessment.class));
    }

    @Test
    void getAllAssessments_Successful() {
        Assessment assessment1 = new Assessment();
        Assessment assessment2 = new Assessment();

        List<Assessment> expectedAssessments = Arrays.asList(assessment1, assessment2);

        when(jpaAssessmentRepository.findAll()).thenReturn(expectedAssessments);

        List<AssessmentDTO> actualAssessments = assessmentService.getAllAssessments();


        assertNotNull(actualAssessments);
        assertEquals(2, actualAssessments.size());
        assertEquals(expectedAssessments.stream()
                .map(assessmentMapper::assessmentToAssessmentDTO)
                .collect(Collectors.toList()), actualAssessments);

        verify(jpaAssessmentRepository, times(1)).findAll();
    }

    @Test
    void getAllAssessments_NotSuccessful() {
        when(jpaAssessmentRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> assessmentService.getAllAssessments());
        verify(jpaAssessmentRepository, times(1)).findAll();
    }

    @Test
    void getAssessmentByIdDTO_Successful() {
        Assessment assessment = new Assessment();
        assessment.setAssessment_content("content");
        AssessmentDTO assessmentDTO = new AssessmentDTO();
        when(jpaAssessmentRepository.findById(1)).thenReturn(Optional.of(assessment));
        when(assessmentMapper.assessmentToAssessmentDTO(assessment)).thenReturn(assessmentDTO);

        AssessmentDTO result = assessmentService.getAssessmentByIdDto(1);
        System.out.println(result + " " +assessmentDTO);
        assertEquals(assessmentDTO, result);

    }

    @Test
    void getAssessmentByIdDTO_NotSuccessful() {
        when(jpaAssessmentRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class , () -> assessmentService.getAssessmentByIdDto(1));
    }
}