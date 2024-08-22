package com.sumerge.controllers;


import com.sumerge.services.AssessmentService;
import com.sumerge.task3.DatabaseClasses.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/all")
    public List<Assessment> getAllRatings() {
        return assessmentService.getAllAssessments();
    }

    @GetMapping("/{id}")
    public Assessment getRating(@PathVariable int id) {
        return assessmentService.getAssessmentbyId(id);
    }

    @PostMapping("/add")
    public Assessment addRating(@RequestBody Assessment assessment) {
        return assessmentService.addAssessment(assessment);
    }

}
