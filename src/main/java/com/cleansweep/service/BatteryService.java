package com.cleansweep.service;

import com.cleansweep.service.interfaces.PowerManagementService;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * Service responsible for managing the battery of the Clean Sweep vacuum.
 *
 * Responsibilities:
 * - Tracks and manages the battery level of the vacuum.
 * - Determines if there is sufficient power for the vacuum to continue cleaning.
 * - Handles battery consumption and recharging processes.
 *
 * Attributes:
 * - batteryLevel: Represents the current battery level of the vacuum, with a maximum of 250 units.
 *
 * Methods:
 * - hasSufficientPower(): Checks if the battery level is above the threshold to continue operating (20 units).
 * - recharge(): Recharges the battery to the maximum level of 250 units.
 * - consumePower(int amount): Consumes a specified amount of power from the battery.
 * - getBatteryLevel(): Retrieves the current battery level.
 */

@Getter
@Service
public class BatteryService implements PowerManagementService {

    //this is the compulsory requirement
    private int batteryLevel = 250;

    @Override
    public boolean hasSufficientPower() {
        return batteryLevel > 20;
    }

    @Override
    public void recharge() {
        batteryLevel = 250;
    }

    public void consumePower(int amount) {
        batteryLevel -= amount;
    }

}
