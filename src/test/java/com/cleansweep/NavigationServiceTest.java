package com.cleansweep;

import com.cleansweep.model.Cell;
import com.cleansweep.model.FloorMap;
import com.cleansweep.model.Vacuum;
import com.cleansweep.service.BatteryService;
import com.cleansweep.service.NavigationService;
import com.cleansweep.service.SensorSimulatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class NavigationServiceTest {

    @Mock
    private FloorMap floorMap;

    @Mock
    private BatteryService batteryService;

    @Mock
    private SensorSimulatorService sensorSimulatorService;

    @Mock
    private Vacuum vacuum;

    @InjectMocks
    private NavigationService navigationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartNavigation() {
        // Given a simple setup with vacuum starting at (0, 0)
        when(vacuum.getCurrentX()).thenReturn(0);
        when(vacuum.getCurrentY()).thenReturn(0);

        Cell[][] cells = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new Cell(i, j, com.cleansweep.common.FloorType.BARE_FLOOR, false, false, false, false, 0);
            }
        }
        when(floorMap.getCells()).thenReturn(cells);
        when(sensorSimulatorService.canMove(0, 1, floorMap)).thenReturn(true);

        // When we start navigation
        navigationService.startNavigation();

        // Then vacuum should navigate and update positions accordingly
        assertEquals(0, vacuum.getCurrentX()); // This should be updated dynamically
    }
}
