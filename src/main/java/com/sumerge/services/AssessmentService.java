package com.sumerge.services;

import com.sumerge.exceptions.NotFoundException;
import com.sumerge.repos.JPAAssessmentRepository;
import com.sumerge.task3.DatabaseClasses.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentService {

    @Autowired
    JPAAssessmentRepository jpaAssessmentRepository;

    public Assessment getAssessmentbyId(int id) {
        Optional<Assessment> assessment = jpaAssessmentRepository.findById(id);
        return assessment.orElseThrow( () -> new NotFoundException("No Assessment with such id: " + id));
    }

    public Assessment addAssessment(Assessment assessment) {
        if (assessment.getAssessment_content() == null || assessment.getAssessment_content().isEmpty()) {
            throw new IllegalArgumentException("Assessment's content is empty");
        }
        return jpaAssessmentRepository.save(assessment);
    }

    public List<Assessment> getAllAssessments() {
        List<Assessment> assessments = jpaAssessmentRepository.findAll();
        if(assessments.isEmpty()){
            throw new NotFoundException("No Assessments found");
        }
        return assessments;
    }
}
