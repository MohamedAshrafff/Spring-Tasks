package com.sumerge.services;

import com.sumerge.exceptions.AlreadyExistException;
import com.sumerge.exceptions.NotFoundException;
import com.sumerge.mappers.CourseMapper;
import com.sumerge.repos.JPAAssessmentRepository;
import com.sumerge.repos.JPAAuthorRepository;
import com.sumerge.repos.JPACourseRepository;
import com.sumerge.repos.JPARatingRepository;
import com.sumerge.task3.CourseRecommender;
import com.sumerge.task3.DatabaseClasses.*;
import com.sumerge.task3.DTOs.CourseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CourseServiceTest {


    @Mock
    private JPACourseRepository jpaCourseRepository;

    @Mock
    private JPAAuthorRepository jpaAuthorRepository;

    @Mock
    private JPARatingRepository jpaRatingRepository;

    @Mock
    private JPAAssessmentRepository jpaAssessmentRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private CourseRecommender courseRecommender;

    @Mock
    private AuthorService authorService;

    @Mock
    private RatingService ratingService;

    @Mock
    private AssessmentService assessmentService;

    private CourseService courseService;

    @BeforeEach
    void setUp() {
        // Manually inject the dependencies
        courseService = new CourseService(courseRecommender);
        courseService.courseMapper = courseMapper;
        courseService.jpaCourseRepository = jpaCourseRepository;
        courseService.jpaAuthorRepository = jpaAuthorRepository;
        courseService.jpaRatingRepository = jpaRatingRepository;
        courseService.jpaAssessmentRepository = jpaAssessmentRepository;
        courseService.authorService = authorService;
        courseService.ratingService = ratingService;
        courseService.assessmentService = assessmentService;
    }

    @Test
    void getCourseById_Found() {
        Course course = new Course();
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        assertDoesNotThrow(() -> courseService.getCourseById(1));
    }

    @Test
    void getCourseByIdNotFound() {
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> courseService.getCourseById(1));
    }

    @Test
    void deleteCourseSuccessful() {
        Course course = new Course();
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        courseService.deleteCourse(1);
        assertEquals(course, courseService.getCourseById(1));
    }

    @Test
    void deleteCourseNotSuccessful() {
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> courseService.deleteCourse(1));
    }

    @Test
    void updateCourseSuccessful() {
        Course course = new Course();
        course.setCourse_name("Course 1");

        Course updatedCourse = new Course();
        course.setCourse_name("Course 2");

        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(jpaCourseRepository.save(course)).thenReturn(updatedCourse);

        CourseDTO updatedCourseFound = courseService.updateCourse(1, course);

        assertEquals(courseMapper.courseToCourseDTO(updatedCourse), updatedCourseFound);
    }

    @Test
    void updateCourseNotSuccessful() {
        Course course = new Course();
        course.setCourse_name("Course 1");

        when(jpaCourseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> courseService.updateCourse(1, course));
        verify(jpaCourseRepository , never()).save(any(Course.class));
    }

    @Test
    void addCourse_Successful() {
        Course course = new Course();
        course.setCourse_name("Course 1");
        course.setCourse_description("description");
        course.setCourse_id(0);
        when(jpaCourseRepository.save(course)).thenReturn(course);
        assertDoesNotThrow(() -> courseService.addCourse(course));
        verify(jpaCourseRepository , times(1)).save(course);
    }
    @Test
    void addCourse_NotSuccessfulNoDescription() {
        Course course = new Course();
        course.setCourse_name("Course 1");
        assertThrows(IllegalArgumentException.class, () -> courseService.addCourse(course));
        verify(jpaCourseRepository , times(0)).save(course);
    }

    @Test
    void addCourse_NotSuccessfulNoName() {
        Course course = new Course();
        course.setCourse_description("description");
        assertThrows(IllegalArgumentException.class, () -> courseService.addCourse(course));
        verify(jpaCourseRepository , times(0)).save(course);
    }

    @Test
    void addCourse_NotSuccessfulAlreadyIdExist() {
        Course course = new Course();
        course.setCourse_id(1);
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        assertThrows(AlreadyExistException.class, () -> courseService.addCourse(course));
        verify(jpaCourseRepository , times(0)).save(course);
    }

    @Test
    void getCourseDTOById_Successful() {
        Course course = new Course();
        course.setCourse_name("Course 1");
        course.setCourse_description("Course description");

        CourseDTO expectedDTO = new CourseDTO();
        expectedDTO.setCourse_name("Course 1");
        expectedDTO.setCourse_description("Course description");

        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(courseMapper.courseToCourseDTO(course)).thenReturn(expectedDTO);

        CourseDTO actualDTO = courseService.getCourseDTOById(1);

        assertNotNull(actualDTO);
    }

    @Test
    void getCourseDTOById_NotFound() {
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.empty());
         assertThrows(NotFoundException.class, () -> {courseService.getCourseDTOById(1);});
    }


    @Test
    void getPaginatedCourses_Successful() {
        Course course1 = new Course("Course 1", "Description 1", 7);
        Course course2 = new Course("Course 2", "Description 2", 3);
        Page<Course> coursesPage = new PageImpl<>(Arrays.asList(course1, course2));
        PageRequest pageable = PageRequest.of(0, 10);
        when(jpaCourseRepository.findAll(pageable)).thenReturn(coursesPage);
        assertDoesNotThrow(() -> courseService.getPaginatedCourses(0,10));
        verify(jpaCourseRepository , times(1)).findAll(pageable);
    }

    @Test
    void getPaginatedCourses_NotSuccessfulWrongPageNumber() {
        PageRequest pageable = PageRequest.of(0, 10);
        assertThrows(IllegalArgumentException.class, () -> {courseService.getPaginatedCourses(-1,10);});
        verify(jpaCourseRepository , times(0)).findAll(pageable);
    }

    @Test
    void getPaginatedCourses_NotSuccessfulWrongSizeNumber() {
        PageRequest pageable = PageRequest.of(0, 10);
        assertThrows(IllegalArgumentException.class, () -> {courseService.getPaginatedCourses(1,0);});
        verify(jpaCourseRepository , times(0)).findAll(pageable);
    }

    @Test
    void getRecommendedCourses_Successful() {
        Course course1 = new Course("Course 1", "Description 1", 7);
        Course course2 = new Course("Course 2", "Description 2", 3);
        when(courseRecommender.recommendedCourses()).thenReturn(Arrays.asList(course1, course2));
        assertDoesNotThrow(() -> courseService.getRecommendedCourses());
    }

    @Test
    void getRecommendedCourses_NotSuccessful() {
        Course course1 = new Course("Course 1", "Description 1", 7);
        Course course2 = new Course("Course 2", "Description 2", 3);
        when(courseRecommender.recommendedCourses()).thenReturn(Arrays.asList(course1, course2));
        assertDoesNotThrow(() -> courseService.getRecommendedCourses());
    }

    @Test
    void addAuthorToCourse_SuccessfulFoundUniqueAuthorAndCourse() {
        Course course = new Course("Course 1", "Description 1", 7);
        Author author = new Author("Author 1", "ahmed@test.com",  "18-5-2002");
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(authorService.getAuthorById(1)).thenReturn(author);
        assertDoesNotThrow(() -> courseService.addAuthorToCourse(1 ,1));
        verify(jpaCourseRepository , times(1)).save(course);
        verify(jpaAuthorRepository , times(1)).save(author);
    }

    @Test
    void addAuthorToCourse_NotSuccessfulFoundNonUniqueAuthorAndCourse() {
        Course course = new Course("Course 1", "Description 1", 7);
        Author author = new Author("Author 1", "ahmed@test.com",  "18-5-2002");
       course.getAuthors().add(author);
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(authorService.getAuthorById(1)).thenReturn(author);
        assertThrows( AlreadyExistException.class, () -> courseService.addAuthorToCourse(1 ,1));
        verify(jpaCourseRepository , times(0)).save(course);
        verify(jpaAuthorRepository , times(0)).save(author);
    }

    @Test
    void addAuthorToCourse_NotSuccessfulNotFoundCourseOrAuthor() {
        Course course = new Course("Course 1", "Description 1", 7);
        Author author = new Author("Author 1", "ahmed@test.com",  "18-5-2002");
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows( NotFoundException.class, () -> courseService.addAuthorToCourse(1 ,1));
        verify(jpaCourseRepository , times(0)).save(course);
        verify(jpaAuthorRepository , times(0)).save(author);
    }

    @Test
    void addRatingToCourse_SuccessfulFoundUniqueRatingAndCourse() {
        Course course = new Course("Course 1", "Description 1", 7);
        Rating rating = new Rating();
        rating.setRating_number(6);
        rating.setCourse(course);
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(ratingService.getRatingById(1)).thenReturn(rating);
        assertDoesNotThrow(() -> courseService.addRatingToCourse(1 ,1));
        verify(jpaRatingRepository , times(1)).save(rating);
        verify(jpaCourseRepository , times(1)).save(course);
    }

    @Test
    void addRatingToCourse_NotSuccessfulFoundNonUniqueRatingAndCourse() {
        Course course = new Course("Course 1", "Description 1", 7);
        Rating rating = new Rating();
        rating.setRating_number(6);
        rating.setCourse(course);
        course.getRatings().add(rating);
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(ratingService.getRatingById(1)).thenReturn(rating);
        assertThrows(AlreadyExistException.class,() -> courseService.addRatingToCourse(1 ,1));
        verify(jpaRatingRepository , times(0)).save(rating);
        verify(jpaCourseRepository , times(0)).save(course);
    }

    @Test
    void addRatingToCourse_NotSuccessfulNotFoundRatingOrCourse() {
        Course course = new Course("Course 1", "Description 1", 7);
        Rating rating = new Rating();
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,() -> courseService.addRatingToCourse(1 ,1));
        verify(jpaRatingRepository , times(0)).save(rating);
        verify(jpaCourseRepository , times(0)).save(course);
    }

    @Test
    void addAuthorToCourse_SuccessfulUniqueAssessmentAndCourseFound() {
        Course course = new Course("Course 1", "Description 1", 7);
        course.setAssessment(null);
        Assessment assessment = new Assessment();
        assessment.setAssessment_content("assessment content");
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(assessmentService.getAssessmentById(1)).thenReturn(assessment);
        assertDoesNotThrow(() -> courseService.addAssessmentToCourse(1 ,1));
        verify(jpaCourseRepository , times(1)).save(course);
        verify(jpaAssessmentRepository , times(1)).save(assessment);
    }

    @Test
    void addAuthorToCourse_NotSuccessfulNonUniqueAssessment() {
        Course course = new Course("Course 1", "Description 1", 7);
        Assessment assessment = new Assessment();
        assessment.setAssessment_content("assessment content");
        course.setAssessment(assessment);
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(assessmentService.getAssessmentById(1)).thenReturn(assessment);
        assertThrows(AlreadyExistException.class,() -> courseService.addAssessmentToCourse(1 ,1));
        verify(jpaCourseRepository , times(0)).save(course);
        verify(jpaAssessmentRepository , times(0)).save(assessment);
    }

    @Test
    void addAuthorToCourse_NotSuccessfulNotFoundAssessmentOrCourse() {
        Course course = new Course("Course 1", "Description 1", 7);
        Assessment assessment = new Assessment();
        assessment.setAssessment_content("assessment content");
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,() -> courseService.addAssessmentToCourse(1 ,1));
        verify(jpaCourseRepository , times(0)).save(course);
        verify(jpaAssessmentRepository , times(0)).save(assessment);
    }

    @Test
    void getCourseByIdDTO_Successful() {
        Course course = new Course();
        course.setCourse_description("content");
        CourseDTO courseDTO = new CourseDTO();
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.of(course));
        when(courseMapper.courseToCourseDTO(course)).thenReturn(courseDTO);

        CourseDTO result = courseService.getCourseByIdDTO(1);
        System.out.println(result + " " +courseDTO);
        assertEquals(courseDTO, result);

    }

    @Test
    void getAssessmentByIdDTO_NotSuccessful() {
        when(jpaCourseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class , () -> courseService.getCourseByIdDTO(1));
    }


}