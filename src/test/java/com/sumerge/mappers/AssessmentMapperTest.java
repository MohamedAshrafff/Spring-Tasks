package com.sumerge.mappers;

import com.sumerge.task3.DatabaseClasses.Assessment;
import com.sumerge.task3.DTOs.AssessmentDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentMapperTest {

    private final AssessmentMapper assessmentMapper = Mappers.getMapper(AssessmentMapper.class);

    @Test
    void assessmentToAssessmentDTO() {
        Assessment assessment = new Assessment();
        assessment.setAssessment_id(1);
        assessment.setAssessment_content("Sample assessment content");

        AssessmentDTO assessmentDTO = assessmentMapper.assessmentToAssessmentDTO(assessment);

        assertNotNull(assessmentDTO);
        assertEquals(1, assessmentDTO.getAssessment_id());
        assertEquals("Sample assessment content", assessmentDTO.getAssessment_content());
    }

    @Test
    void assessmentToAssessmentDTO_NullAssessment_ShouldReturnNull() {
        AssessmentDTO assessmentDTO = assessmentMapper.assessmentToAssessmentDTO(null);

        assertNull(assessmentDTO);
    }
}
