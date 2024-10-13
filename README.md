# Clean Sweep System

This project is designed to simulate an intelligent robot vacuum cleaner, named **Clean Sweep**, that autonomously navigates and cleans a typical household environment. The project implements several core components, including **Control System**, **Sensor Simulator**, **Power Management**, and **Navigation System**. The project is developed using **Spring Boot** and **Java**, utilizing clean architecture principles to demonstrate effective object-oriented design and modularity.

## Project Overview

The **Clean Sweep System** is composed of several modules that work together to provide functionality for:

- **Navigation and Control**: Allows the vacuum to autonomously move around the floor plan, avoid obstacles, and return to its charging station when low on power.
- **Dirt Detection and Cleaning**: Detects and cleans dirt based on the current surface type and the amount of dirt present.
- **Power Management**: Manages battery life based on the surface type, navigation, and cleaning requirements.
- **Sensor Simulation**: Simulates sensor data, including obstacle detection, surface types, and dirt levels, to allow the control system to react as if operating in a real-world environment.

The system is built to follow agile methodologies and object-oriented principles, ensuring high modularity and maintainability

## Project Structure

The **Clean Sweep System** has a modular structure, as follows:

### 1. Common
- **ConfigManager**: Manages configuration settings for the system, such as file paths for loading floor plans.
- **Direction**: Enum representing the directions in which the vacuum can move (e.g., UP, DOWN, LEFT, RIGHT).
- **FloorType**: Enum representing different types of floor surfaces (e.g., BARE_FLOOR, LOW_PILE_CARPET, HIGH_PILE_CARPET).
- **Utilities**: General-purpose utility methods used across the system, including logging.

### 2. Model
- **Cell**: Represents an individual cell in the floor map, with properties like floor type, dirt level, obstacles, etc.
- **FloorMap**: Represents the entire floor plan, with methods for loading the map from a file.
- **Vacuum**: Represents the vacuum cleaner's current state, including position and methods for movement.

### 3. Controller
- **CleanSweepController**: The main controller responsible for managing the initialization of the system and starting the cleaning process.

### 4. Service
- **NavigationService**: Handles the navigation of the vacuum cleaner, using a BFS algorithm to traverse the floor map.
- **BatteryService**: Manages the vacuum's battery, including consumption and recharging.
- **SensorSimulatorService**: Simulates the behavior of the vacuum's sensors, including detecting obstacles, dirt, and surface types.

### 5. Service Interfaces
- **PowerManagementService**: Interface to manage power-related functions for the vacuum cleaner.

### 6. Tests
- **CleanSweepSystemApplicationTests**: Test cases for the main application logic.
- **NavigationServiceTest**: Tests for the navigation functionality.
- **SensorSimulatorTest**: Tests for the sensor simulation logic.

## UML Diagram for Sprint 1
![UML digram - sprint 1](https://github.com/user-attachments/assets/859be0d3-6de2-4b4d-9ef0-4737e1679d5f)

## Running the Project
![44d35bae3f5fb42113b571b762ddd66](https://github.com/user-attachments/assets/e63f49b4-690f-4be3-8151-587481168af2)

## Testing the Project
![1dbf6e29c38a125f559a7d2b4ae87e6](https://github.com/user-attachments/assets/bb1f31a6-3e43-4ecb-aadd-423e18edfcee)

### Prerequisites
- **Java 17**
- **Maven**

### Build and Run the Application
To build and run the application, use the following command:
```sh
mvn spring-boot:run

