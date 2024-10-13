package com.cleansweep.model;

import com.cleansweep.common.FloorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a single cell in the floor plan.
 *
 * Responsibilities:
 * - Holds information about a specific location on the floor, such as its type, position, and dirt level.
 * - Tracks the state of the cell, including if it has been visited, if it is an obstacle, or if it is a charging station.
 *
 * Attributes:
 * - x, y: Coordinates of the cell in the floor plan grid.
 * - floorType: The type of floor (bare floor, low-pile carpet, high-pile carpet).
 * - isVisited: Whether the cell has been visited by the vacuum.
 * - isChargingStation: Indicates if this cell is a charging station.
 * - isObstacle: Indicates if this cell is an obstacle that cannot be traversed.
 * - isStairs: Indicates if this cell contains stairs that cannot be traversed.
 * - dirtLevel: Represents how much dirt is on the cell, which needs to be cleaned by the vacuum.
 *
 * Methods:
 * - reduceDirtLevel(): Decreases the dirt level in the cell by 1 unit, if the dirt level is greater than 0.
 */

@Getter
@Setter
@AllArgsConstructor
public class Cell {
    private int x;
    private int y;
    private FloorType floorType;
    private boolean isVisited;
    private boolean isChargingStation;
    private boolean isObstacle;
    private boolean isStairs;
    private int dirtLevel;

    public void reduceDirtLevel() {
        if (dirtLevel > 0) {
            dirtLevel--;
        }
    }
}
