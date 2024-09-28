package com.cleansweep;

import com.cleansweep.controller.CleanSweepController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CleanSweepSystemApplication is the main entry point for the Clean Sweep robotic vacuum system.
 *
 * Responsibilities:
 * - Bootstraps the Spring Boot application.
 * - Implements CommandLineRunner to perform actions immediately after the application context is loaded.
 *
 * Dependencies:
 * - CleanSweepController: Handles the initialization and navigation of the cleaning process.
 */
@SpringBootApplication
public class CleanSweepSystemApplication implements CommandLineRunner {

	@Autowired
	private CleanSweepController cleanSweepController;

	public static void main(String[] args) {
		SpringApplication.run(CleanSweepSystemApplication.class, args);
	}

	// Automatically call initializeSystem() and startCleaning() after Spring Boot has finished starting up
	@Override
	public void run(String... args) throws Exception {
		cleanSweepController.initializeSystem();
		cleanSweepController.startCleaning();
	}
}