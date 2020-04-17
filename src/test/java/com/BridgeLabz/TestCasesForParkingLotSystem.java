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
    public void givenAVehicle_WhenPark_ShouldReturnTrue() throws ParkingLotSystemException {
        boolean parkVehicle = parkingLotSystem.park(new Object());
        Assert.assertTrue(parkVehicle);

    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking is full", e.message);
        }
    }

    @Test
    public void givenAVehicle_WhenUnpark_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
        boolean unParkVehicle = parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(unParkVehicle);
    }


    @Test
    public void givenVehicle_WhenAlreadyUnParked_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
        boolean unParkVehicle = parkingLotSystem.unPark(new Object());
        Assert.assertFalse(unParkVehicle);
    }
}
