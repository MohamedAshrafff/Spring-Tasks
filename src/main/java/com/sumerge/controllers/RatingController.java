package com.sumerge.controllers;


import com.sumerge.services.RatingService;
import com.sumerge.task3.DatabaseClasses.Rating;
import com.sumerge.task3.DTOs.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("/all")
    public List<RatingDTO> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public RatingDTO getRating(@PathVariable int id) {
        return ratingService.getRatingByIdDTO(id);
    }

    @PostMapping("/add")
    public ResponseEntity<RatingDTO> addRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.addRating(rating) , HttpStatus.CREATED);
    }
}
