package com.sumerge.mappers;


import com.sumerge.task3.DatabaseClasses.Rating;
import com.sumerge.task3.DTOs.RatingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(source = "rating_id" , target = "rating_id")
    @Mapping(source = "rating_number" , target = "rating_number")
    @Mapping(source = "course" , target = "course")
    RatingDTO ratingToRatingDTO(Rating rating);
}