package com.cleansweep.service;

import com.cleansweep.common.Direction;
import com.cleansweep.common.FloorType;
import com.cleansweep.common.Utilities;
import com.cleansweep.model.Cell;
import com.cleansweep.model.FloorMap;
import com.cleansweep.model.Vacuum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Responsibilities:
 * - Using BFS algorithm to check the grids
 * - I tend to use bfs instead of dfs cuz dfs refers to recursion, and it will occupy the space in the factory code,
 * - After all, it's not a LeetCode
 */

@Service
public class NavigationService {

    @Autowired
    private FloorMap floorMap;

    @Autowired
    private BatteryService batteryService;

    @Autowired
    private SensorSimulatorService sensorSimulatorService;

    @Autowired
    private Vacuum vacuum;

    public void startNavigation() {

        Deque<Cell> queue = new ArrayDeque<>();
        Cell startCell = floorMap.getCells()[vacuum.getCurrentX()][vacuum.getCurrentY()];
        queue.add(startCell);
        startCell.setVisited(true);

        Utilities.log("Starting BFS navigation...");

        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();
            Utilities.log("Visiting cell at (" + currentCell.getX() + ", " + currentCell.getY() + ")");
            vacuum.setCurrentX(currentCell.getX());
            vacuum.setCurrentY(currentCell.getY());

            cleanCurrentCell(currentCell);

            for (Direction direction : Direction.values()) {
                int newX = currentCell.getX() + direction.getXOffset();
                int newY = currentCell.getY() + direction.getYOffset();
                if (isValidMove(newX, newY)) {
                    Cell neighborCell = floorMap.getCells()[newX][newY];

                    if (neighborCell == null) {
                        Utilities.log("Encountered an uninitialized cell at (" + newX + ", " + newY + "). Skipping...");
                        continue;
                    }

                    if (!neighborCell.isVisited() && sensorSimulatorService.canMove(newX, newY, floorMap)) {
                        queue.add(neighborCell);
                        neighborCell.setVisited(true);
                    } else if (!sensorSimulatorService.canMove(newX, newY, floorMap)) {
                        Utilities.log("Obstacle encountered at (" + newX + ", " + newY + "), changing direction.");
                    }
                }

            }

            // Check battery level and return to charging station if below threshold
            if (!batteryService.hasSufficientPower()) {
                Utilities.log("Battery low, returning to charging station.");
                returnToChargingStation();
                break;
            }
        }

        Utilities.log("Cleaning process finished or paused due to low battery.");
    }

    private void cleanCurrentCell(Cell cell) {
        if (cell.getDirtLevel() > 0) {
            Utilities.log("Cleaning cell at (" + cell.getX() + ", " + cell.getY() + ")");
            while (cell.getDirtLevel() > 0) {
                cell.reduceDirtLevel();
                batteryService.consumePower(getPowerConsumption(cell.getFloorType()));
                Utilities.log("Battery level after cleaning: " + batteryService.getBatteryLevel());
            }
        }
    }

    private int getPowerConsumption(FloorType floorType) {
        switch (floorType) {
            case BARE_FLOOR:
                return 1;
            case LOW_PILE_CARPET:
                return 2;
            case HIGH_PILE_CARPET:
                return 3;
            default:
                return 1;
        }
    }

    //TODO:Implement a pathfinding algorithm like A* or Dijkstra's to locate the nearest charging station
    // based on the current position, ensuring efficient power management.
    // For now, I just simplify to set it to (0, 0)
    private void returnToChargingStation() {
        vacuum.setCurrentX(0);
        vacuum.setCurrentY(0);
        batteryService.recharge();
        Utilities.log("Charging completed. Resuming cleaning...");
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < floorMap.getCells().length && y >= 0 && y < floorMap.getCells()[0].length;
    }
}
