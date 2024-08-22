package com.sumerge.repos;

import com.sumerge.task3.DatabaseClasses.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARatingRepository extends JpaRepository<Rating, Integer> {
}
