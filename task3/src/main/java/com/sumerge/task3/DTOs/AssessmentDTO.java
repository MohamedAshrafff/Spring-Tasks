package com.sumerge.task3.DTOs;

public class AssessmentDTO {
    private int assessment_id;
    private String assessment_content;

    public AssessmentDTO() {}

    public AssessmentDTO(int assessment_id, String assessment_content) {
        this.assessment_id = assessment_id;
        this.assessment_content = assessment_content;
    }

    public int getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(int assessment_id) {
        this.assessment_id = assessment_id;
    }

    public String getAssessment_content() {
        return assessment_content;
    }

    public void setAssessment_content(String assessment_content) {
        this.assessment_content = assessment_content;
    }
}
