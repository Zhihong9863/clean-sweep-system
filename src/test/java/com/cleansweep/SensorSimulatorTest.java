package com.cleansweep;

<<<<<<< HEAD
import com.cleansweep.model.Cell;
import com.cleansweep.model.FloorMap;
import com.cleansweep.service.SensorSimulatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SensorSimulatorTest {

    private SensorSimulatorService sensorSimulatorService;
    private FloorMap floorMap;

    @BeforeEach
    void setup() {
        sensorSimulatorService = new SensorSimulatorService();
        floorMap = new FloorMap();

        // Setup a simple 5x5 floor map for testing
        Cell[][] cells = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new Cell(i, j, com.cleansweep.common.FloorType.BARE_FLOOR, false, false, false, false, 0);
            }
        }
        // Set some obstacles
        cells[2][2].setObstacle(true);
        floorMap.setCells(cells);
    }

    @Test
    void testCanMove() {
        // Test movement into an obstacle
        assertFalse(sensorSimulatorService.canMove(2, 2, floorMap));

        // Test valid movement
        assertTrue(sensorSimulatorService.canMove(0, 1, floorMap));
    }
=======
public class SensorSimulatorTest {
>>>>>>> 8276857 (Initial commit with project structure and dependencies)
}
