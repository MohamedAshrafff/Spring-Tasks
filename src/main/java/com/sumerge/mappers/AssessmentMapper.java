package com.sumerge.mappers;

import com.sumerge.task3.DatabaseClasses.Assessment;
import com.sumerge.task3.DTOs.AssessmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AssessmentMapper {

    @Mapping(source = "assessment_id" , target = "assessment_id")
    @Mapping(source = "assessment_content" , target = "assessment_content")

    AssessmentDTO assessmentToAssessmentDTO(Assessment assessment);
}
