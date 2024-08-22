package com.sumerge.repos;

import com.sumerge.task3.DatabaseClasses.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAAssessmentRepository extends JpaRepository<Assessment, Integer> {

}
