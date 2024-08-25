package com.sumerge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumerge.exceptions.NotFoundException;
import com.sumerge.services.RatingService;
import com.sumerge.task3.DatabaseClasses.Rating;
import com.sumerge.task3.DTOs.RatingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RatingController.class)
@ContextConfiguration(classes =RatingController.class)
@ComponentScan("com.sumerge.exceptions")
class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Autowired
    private ObjectMapper objectMapper;

    private Rating rating;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rating = new Rating();
        rating.setRating_number(7);
    }
    @Test
    void getAllRatings_Successful() throws Exception {
        List<RatingDTO> ratings = new ArrayList<>();
        RatingDTO rating1 = new RatingDTO();
        ratings.add(new RatingDTO());
        ratings.add(rating1);
        when(ratingService.getAllRatings()).thenReturn(ratings);
        mockMvc.perform(get("/api/ratings/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllRatings_NotSuccessful() throws Exception {
        when(ratingService.getAllRatings()).thenThrow(new NotFoundException("NO Ratings Found"));
        mockMvc.perform(get("/api/ratings/all"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getRatingById_Successful() throws Exception {
        when(ratingService.getRatingById(1)).thenReturn(rating);
        mockMvc.perform(get("/api/ratings/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getRatingById_NotSuccessful() throws Exception {
        when(ratingService.getRatingByIdDTO(1)).thenThrow(new NotFoundException("NO Rating Found"));
        mockMvc.perform(get("/api/ratings/1"))
                .andExpect(status().isNotFound());
    }


    @Test
    void addRating_Successful() throws Exception {
        when(ratingService.addRating(rating)).thenReturn(new RatingDTO() );
        mockMvc.perform(post("/api/ratings/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rating)))
                .andExpect(status().isCreated());
    }

    @Test
    void addRating_NotSuccessful() throws Exception {
        when(ratingService.addRating(rating)).thenThrow(new IllegalArgumentException("wrong or empty rating value"));
        mockMvc.perform(post("/api/ratings/add"))
                .andExpect(status().isBadRequest());
    }
}