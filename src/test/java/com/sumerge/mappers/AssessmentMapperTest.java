package com.sumerge.mappers;

import com.sumerge.task3.DatabaseClasses.Assessment;
import com.sumerge.task3.DTOs.AssessmentDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AssessmentMapperTest {

    @Autowired
    private AssessmentMapper assessmentMapper;

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
