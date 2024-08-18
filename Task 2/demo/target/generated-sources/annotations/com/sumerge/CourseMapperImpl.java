package com.sumerge;

import com.sumerge.task3.DatabaseClasses.Course;
import com.sumerge.task3.DatabaseClasses.CourseDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-18T22:56:57+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO courseToCourseDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setCourse_id( course.getCourse_id() );
        courseDTO.setCourse_name( course.getCourse_name() );
        courseDTO.setCourse_description( course.getCourse_description() );

        return courseDTO;
    }
}
