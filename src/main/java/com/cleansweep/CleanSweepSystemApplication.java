package com.cleansweep;

import com.cleansweep.controller.CleanSweepController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//runs once after the application context is loaded
@SpringBootApplication
public class CleanSweepSystemApplication implements CommandLineRunner {

	@Autowired
	private CleanSweepController cleanSweepController;

	public static void main(String[] args) {
		SpringApplication.run(CleanSweepSystemApplication.class, args);
	}

	//automatically call initializeSystem() and startCleaning() after Spring Boot has finished starting up
	@Override
	public void run(String... args) throws Exception {
		cleanSweepController.initializeSystem();
		cleanSweepController.startCleaning();
	}
}
