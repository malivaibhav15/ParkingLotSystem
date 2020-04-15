package com.BridgeLabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCasesForParkingLotSystem {

    Object vehicle = null;
    ParkingLotSystem parkingLotSystem = null;

    @Before
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenAVehicle_WhenPark_ShouldReturnTrue() {
        boolean parkVehicle = parkingLotSystem.park(new Object());
        Assert.assertTrue(parkVehicle);

    }

    @Test
    public void givenAVehicle_WhenUnpark_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle);
        boolean unParkVehicle = parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(unParkVehicle);
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingLotSystem.park(vehicle);
        boolean isParkVehiclde = parkingLotSystem.park(new Object());
        Assert.assertFalse(isParkVehiclde);
    }

    @Test
    public void givenVehicle_WhenAlreadyUnParked_ShouldReturnFalse() {
        parkingLotSystem.park(vehicle);
        boolean unParkVehicle = parkingLotSystem.unPark(new Object());
        Assert.assertFalse(unParkVehicle);
    }
}
