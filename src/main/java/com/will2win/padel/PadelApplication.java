package com.will2win.padel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//Starting point for Springboot applicaton
public class PadelApplication {

	public static void main(String[] args) {

		System.setProperty("bookingTime",args[0] );
		SpringApplication.run(PadelApplication.class, args);

	}

}
