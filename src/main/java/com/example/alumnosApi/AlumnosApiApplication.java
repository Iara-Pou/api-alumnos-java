package com.example.alumnosApi;

import com.example.alumnosApi.controllers.ExceptionHandlerGlobal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import(ExceptionHandlerGlobal.class)

public class AlumnosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumnosApiApplication.class, args);
	}

}
