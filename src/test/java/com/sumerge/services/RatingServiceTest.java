package com.sumerge.services;

import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.RatingMapper;
import com.sumerge.repos.JPARatingRepository;
import com.sumerge.task3.DatabaseClasses.Rating;
import com.sumerge.task3.DTOs.RatingDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    JPARatingRepository jpaRatingRepository;

    @Mock
    RatingMapper ratingMapper;

    @InjectMocks
    RatingService ratingService;

    @Test
    void getRatingById_Successful() {
       Rating rating = new Rating();
        when(jpaRatingRepository.findById(1)).thenReturn(Optional.of(rating));
        assertDoesNotThrow(()-> ratingService.getRatingById(1));
    }

    @Test
    void getRatingById_NotSuccessful() {
        when(jpaRatingRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class , () -> ratingService.getRatingById(1));
    }

    @Test
    void addRating_Successful_InRange() {
        Rating rating = new Rating();
        rating.setRating_number(2);
        when(jpaRatingRepository.save(rating)).thenReturn(rating);
        assertDoesNotThrow(() -> ratingService.addRating(rating));
        verify(jpaRatingRepository, atLeastOnce()).save(any(Rating.class));
    }

    @Test
    void addRating_NotSuccessfulOutOfRange() {
        Rating rating = new Rating();
        rating.setRating_number(-1);
        assertThrows(IllegalArgumentException.class , () -> ratingService.addRating(rating));
        verify(jpaRatingRepository, times(0)).save(any(Rating.class));
    }

    @Test
    void getAllRatings_Successful() {
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();

        List<Rating> expectedRatings = Arrays.asList(rating1, rating2);

        when(jpaRatingRepository.findAll()).thenReturn(expectedRatings);

        List<RatingDTO> actualAssessments = ratingService.getAllRatings();

        assertNotNull(actualAssessments);
        assertEquals(2, actualAssessments.size());
        assertEquals(expectedRatings.stream()
                .map(ratingMapper::ratingToRatingDTO)
                .collect(Collectors.toList()), actualAssessments);

        verify(jpaRatingRepository, times(1)).findAll();

    }

    @Test
    void getAllRatings_NotSuccessful() {
        when(jpaRatingRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class , () -> ratingService.getAllRatings());
        verify(jpaRatingRepository, times(1)).findAll();

    }

    @Test
    void getRatingByIdDTO_Successful() {
        Rating rating = new Rating();
        rating.setRating_number(2);
        RatingDTO ratingDTO = new RatingDTO();
        when(jpaRatingRepository.findById(1)).thenReturn(Optional.of(rating));
        when(ratingMapper.ratingToRatingDTO(rating)).thenReturn(ratingDTO);

        RatingDTO result = ratingService.getRatingByIdDTO(1);
        System.out.println(result + " " +ratingDTO);
        assertEquals(ratingDTO, result);

    }

    @Test
    void getRatingByIdDTO_NotSuccessful() {
        when(jpaRatingRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class , () -> ratingService.getRatingByIdDTO(1));
    }
}