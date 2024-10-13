package com.cleansweep.controller;

import com.cleansweep.model.FloorMap;
import com.cleansweep.common.Utilities;
import com.cleansweep.common.ConfigManager;
import com.cleansweep.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * CleanSweepController is the main controller for managing the initialization and navigation of the Clean Sweep robotic vacuum system.
 *
 * Responsibilities:
 * 1. Initialize the system by loading the floor plan.
 * 2. Start the cleaning process using the navigation service.
 *
 * Dependencies:
 * - FloorMap: Represents the map of the floor to be cleaned.
 * - NavigationService: Handles the actual cleaning navigation.
 * - Utilities: Provides logging functionality.
 * - ConfigManager: Provides configuration settings, such as file paths for the floor plan.
 *
 * Methods:
 * - initializeSystem(): Loads the floor plan from a JSON file and logs the result.
 * - startCleaning(): Initiates the cleaning process by starting the navigation logic.
 */

@Controller
public class CleanSweepController {

    @Autowired
    private FloorMap floorMap;

    @Autowired
    private NavigationService navigationService;

    public void initializeSystem() {
        String filePath = ConfigManager.getFloorPlanFilePath();
        floorMap.loadFromFile(filePath);
        Utilities.log("Floor map initialized from file: " + filePath);
    }

    public void startCleaning() {
        Utilities.log("Starting the cleaning process...");
        navigationService.startNavigation();  // Start the BFS navigation and cleaning
    }
}
