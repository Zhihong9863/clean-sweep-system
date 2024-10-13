package com.cleansweep.service;

import com.cleansweep.model.Cell;
import com.cleansweep.model.FloorMap;
import org.springframework.stereotype.Service;

/**
 * Service responsible for simulating sensor behaviors of the Clean Sweep vacuum.
 *
 * Responsibilities:
 * - Provides methods to check if the vacuum can move to a specific cell in the floor map.
 * - Simulates the sensor that detects obstacles and stairs in a given cell.
 * - Simulates checking the dirt level at a specific cell location.
 *
 * Methods:
 * - canMove(int x, int y, FloorMap floorMap): Determines if the vacuum can move to the specified coordinates in the floor plan. It checks for boundaries, obstacles, and stairs.
 * - getDirtLevel(int x, int y, FloorMap floorMap): Retrieves the dirt level of a specific cell in the floor map.
 */

@Service
public class SensorSimulatorService {

    public boolean canMove(int x, int y, FloorMap floorMap) {
        if (x < 0 || x >= floorMap.getCells().length || y < 0 || y >= floorMap.getCells()[0].length) {
            return false; // Out of bounds
        }
        Cell cell = floorMap.getCells()[x][y];
        return !cell.isObstacle() && !cell.isStairs();
    }

    public int getDirtLevel(int x, int y, FloorMap floorMap) {
        if (x >= 0 && x < floorMap.getCells().length && y >= 0 && y < floorMap.getCells()[0].length) {
            return floorMap.getCells()[x][y].getDirtLevel();
        }
        return 0;
    }
}
