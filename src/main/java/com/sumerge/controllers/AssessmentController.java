package com.sumerge.controllers;


import com.sumerge.services.AssessmentService;
import com.sumerge.task3.DatabaseClasses.Assessment;
import com.sumerge.task3.DTOs.AssessmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/all")
    public List<AssessmentDTO> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @GetMapping("/{id}")
    public AssessmentDTO getAssessmentById(@PathVariable int id) {
        return assessmentService.getAssessmentByIdDto(id);
    }

    @PostMapping("/add")
    public AssessmentDTO addAssessment(@RequestBody Assessment assessment) {
        return assessmentService.addAssessment(assessment);
    }

}
