package com.BridgeLabz;

import org.junit.Assert;
import org.junit.Test;

public class TestCasesForParkingLotSystem {
    @Test
    public void givenAVehicle_WhenPark_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isPark = parkingLotSystem.park(new Object());
        Assert.assertTrue(isPark);

    }
}
