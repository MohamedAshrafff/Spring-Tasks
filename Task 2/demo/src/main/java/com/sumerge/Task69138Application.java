package com.sumerge;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.sumerge")
public class Task69138Application {


	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		CourseService service = applicationContext.getBean(CourseService.class);
		service.getRecommendedCourses().forEach(course -> System.out.println(course.getCourseName()));
		}

}
