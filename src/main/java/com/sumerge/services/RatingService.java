package com.sumerge.services;

import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.RatingMapper;
import com.sumerge.repos.JPARatingRepository;
import com.sumerge.task3.DatabaseClasses.Rating;
import com.sumerge.task3.DTOs.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    JPARatingRepository jpaRatingRepository;

    @Autowired
    RatingMapper ratingMapper;

    public Rating getRatingById(int id) {
        Optional<Rating> rating = jpaRatingRepository.findById(id);
        return rating.orElseThrow( () -> new NotFoundException("No rating with such id: " + id));
    }

    public RatingDTO getRatingByIdDTO(int id) {
        Optional<Rating> rating = jpaRatingRepository.findById(id);
        return ratingMapper.ratingToRatingDTO(rating
                .orElseThrow(() ->new NotFoundException("No Assessment with such id: " + id)));
    }

    public RatingDTO addRating(Rating rating) {
        if (rating.getRating_number() < 0 || rating.getRating_number() > 10 ){
            throw new IllegalArgumentException("wrong or empty rating score");
        }
        return ratingMapper.ratingToRatingDTO(jpaRatingRepository.save(rating));
    }

    public List<RatingDTO> getAllRatings(){
        List<Rating> ratings = jpaRatingRepository.findAll();
        if(ratings.isEmpty()){
            throw new NotFoundException("No Ratings found");
        }
        return ratings.stream()
                .map(ratingMapper::ratingToRatingDTO)
                .collect(Collectors.toList());
    }

}
