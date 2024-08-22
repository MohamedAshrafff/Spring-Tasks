package com.sumerge.repos;

import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface    JPACourseRepository extends JpaRepository<Course, Integer> {

}
