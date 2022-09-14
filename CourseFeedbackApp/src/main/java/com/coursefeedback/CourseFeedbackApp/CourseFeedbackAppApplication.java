package com.coursefeedback.CourseFeedbackApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CourseFeedbackAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseFeedbackAppApplication.class, args);
	}

}
