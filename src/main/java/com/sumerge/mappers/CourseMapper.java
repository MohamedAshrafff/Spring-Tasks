package com.sumerge.mappers;


import com.sumerge.task3.DatabaseClasses.Course;
import com.sumerge.task3.DTOs.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "course_id", target = "course_id")
    @Mapping(source = "course_name", target = "course_name")
    @Mapping(source = "course_description", target = "course_description")
    CourseDTO courseToCourseDTO(Course course);
}
