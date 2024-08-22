package com.sumerge.controllers;


import com.sumerge.services.RatingService;
import com.sumerge.task3.DatabaseClasses.Rating;
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
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public Rating getRating(@PathVariable int id) {
        return ratingService.getRatingById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.addRating(rating) , HttpStatus.CREATED);
    }
}
