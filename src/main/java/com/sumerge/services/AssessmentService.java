package com.sumerge.services;

import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.AssessmentMapper;
import com.sumerge.repos.JPAAssessmentRepository;
import com.sumerge.task3.DatabaseClasses.Assessment;
import com.sumerge.task3.DTOs.AssessmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssessmentService {

    @Autowired
    JPAAssessmentRepository jpaAssessmentRepository;

    @Autowired
    AssessmentMapper assessmentMapper;

    public AssessmentDTO getAssessmentByIdDto(int id){
        Optional<Assessment> assessment = jpaAssessmentRepository.findById(id);
        return assessmentMapper.assessmentToAssessmentDTO(assessment
                .orElseThrow(() ->new NotFoundException("No Assessment with such id: " + id)));
    }

    public Assessment getAssessmentById(int id) {
        Optional<Assessment> assessment = jpaAssessmentRepository.findById(id);
        return assessment.orElseThrow( () -> new NotFoundException("No Assessment with such id: " + id));
    }

    public AssessmentDTO addAssessment(Assessment assessment) {
        if (assessment.getAssessment_content() == null || assessment.getAssessment_content().isEmpty()) {
            throw new IllegalArgumentException("Assessment's content is empty");
        }
        return assessmentMapper.assessmentToAssessmentDTO(jpaAssessmentRepository.save(assessment));
    }

    public List<AssessmentDTO> getAllAssessments() {
        List<Assessment> assessments = jpaAssessmentRepository.findAll();
        if (assessments.isEmpty()) {
            throw new NotFoundException("No Assessments found");
        }
        return assessments.stream()
                .map(assessmentMapper::assessmentToAssessmentDTO)
                .collect(Collectors.toList());
    }
}
