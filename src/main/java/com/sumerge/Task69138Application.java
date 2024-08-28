package com.sumerge;


import com.sumerge.configs.RestSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@Import(RestSecurityConfig.class)
public class Task69138Application {
	public static void main(String[] args) {

		SpringApplication.run(Task69138Application.class, args);

	}
}
