package com.cleansweep.model;

import com.cleansweep.common.FloorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Represents the entire floor plan for the Clean Sweep vacuum cleaner.
 *
 * Responsibilities:
 * - Manages a 2D array of cells, representing the floor layout.
 * - Loads floor data from a JSON file and initializes the grid based on the given data.
 *
 * Attributes:
 * - cells: A 2D array of Cell objects representing the grid of the floor.
 *
 * Methods:
 * - loadFromFile(String filePath): Loads the floor plan from a JSON file, using an ObjectMapper to parse it.
 * - initializeCells(FloorPlanData floorPlanData): Initializes the floor cells based on the data loaded from the file.
 *
 * Inner Classes:
 * - FloorPlanData: Represents the parsed data structure for the entire floor plan, including floor size and cells.
 * - CellData: Represents a single cell's data from the JSON file, used for initializing cells in the floor plan.
 */

@Getter
@Setter
@Component
public class FloorMap {
    private Cell[][] cells;

    public void loadFromFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FloorPlanData floorPlanData = objectMapper.readValue(new File(filePath), FloorPlanData.class);
            initializeCells(floorPlanData);
        } catch (IOException e) {
            System.err.println("Error loading floor map from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeCells(FloorPlanData floorPlanData) {
        int size = floorPlanData.getFloorSize();
        cells = new Cell[size][size];

        // Initialize the entire grid with default cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j, FloorType.BARE_FLOOR, false, false, false, false, 0);
            }
        }

        // Override specific cells from the provided data
        for (CellData cellData : floorPlanData.getCells()) {
            cells[cellData.getX()][cellData.getY()] = new Cell(
                    cellData.getX(),
                    cellData.getY(),
                    cellData.getFloorType(),
                    false,
                    cellData.isChargingStation(),
                    cellData.isObstacle(),
                    cellData.isStairs(),
                    cellData.getDirtLevel()
            );
        }
    }

    @Getter
    static class FloorPlanData {
        private int floorSize;
        private List<CellData> cells;
    }

    @Getter
    static class CellData {
        private int x;
        private int y;
        private FloorType floorType;

        @JsonProperty("isChargingStation")
        private boolean isChargingStation;

        @JsonProperty("isObstacle")
        private boolean isObstacle;

        @JsonProperty("isStairs")
        private boolean isStairs;

        private int dirtLevel;
    }
}
