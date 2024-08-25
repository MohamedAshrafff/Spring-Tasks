package com.sumerge.mappers;

import com.sumerge.task3.DatabaseClasses.Rating;
import com.sumerge.task3.DTOs.RatingDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class RatingMapperTest {

    private final RatingMapper ratingMapper = Mappers.getMapper(RatingMapper.class);

    @Test
    void ratingToRatingDTO() {
        Rating rating = new Rating();
        rating.setRating_id(1);
        rating.setRating_number(9);

        RatingDTO ratingDTO = ratingMapper.ratingToRatingDTO(rating);

        assertNotNull(ratingDTO);
        assertEquals(1, ratingDTO.getRating_id());
        assertEquals(9, ratingDTO.getRating_number());
    }

    @Test
    void ratingToRatingDTO_NullRating_ShouldReturnNull() {
        // Act
        RatingDTO ratingDTO = ratingMapper.ratingToRatingDTO(null);

        // Assert
        assertNull(ratingDTO);
    }
}
