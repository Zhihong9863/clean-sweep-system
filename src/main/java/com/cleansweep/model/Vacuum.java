package com.cleansweep.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Represents the vacuum cleaner that moves around the floor plan.
 *
 * Responsibilities:
 * - Tracks the current position of the vacuum on the floor.
 * - Allows movement to different cells in the floor plan.
 *
 * Attributes:
 * - currentX, currentY: The current coordinates of the vacuum in the floor plan grid.
 *
 * Methods:
 * - moveTo(int x, int y): Updates the vacuum's position to the given coordinates.
 */

@Getter
@Setter
@Component
public class Vacuum {
    private int currentX = 0;
    private int currentY = 0;

    public void moveTo(int x, int y) {
        this.currentX = x;
        this.currentY = y;
    }
}
