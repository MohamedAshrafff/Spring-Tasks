package com.sumerge;


import com.sumerge.task3.DatabaseClasses.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.sumerge")
public class Task69138Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		CourseService service = applicationContext.getBean(CourseService.class);
		System.out.println(service.getCoursesCount());
		//service.addCourse(new Course("JAVA Spring BOOT" , "dive into deep intro of java spring boot " ,1,5));
		//service.getCourseById(6);
		//service.updateCourse(1,new Course("New Course Added " , "new description" ,1 ,5 ));
		//service.deleteCourse(5);
	}
}
