package com.sumerge.services;

import com.sumerge.exceptions.AlreadyExistException;
import com.sumerge.exceptions.NotFoundException;
import com.sumerge.repos.JPARatingRepository;
import com.sumerge.task3.DatabaseClasses.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    JPARatingRepository jpaRatingRepository;

    public Rating getRatingById(int id) {
        Optional<Rating> rating = jpaRatingRepository.findById(id);
        return rating.orElseThrow( () -> new NotFoundException("No rating with such id: " + id));
    }

    public Rating addRating(Rating rating) {
        if (rating.getRating_number() < 0 || rating.getRating_number() > 10 ){
            throw new IllegalArgumentException("wrong or empty rating score");
        }
        return jpaRatingRepository.save(rating);
    }

    public List<Rating> getAllRatings(){
        List<Rating> ratings = jpaRatingRepository.findAll();
        if(ratings.isEmpty()){
            throw new NotFoundException("No Ratings found");
        }
        return ratings;
    }

}
