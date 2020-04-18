package com.BridgeLabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCasesForParkingLotSystem {
    Object car = null;
    Object bus = null;
    Object vehicle = null;
    ParkingLotSystem parkingLotSystem = null;

    @Before
    public void setUp() {
        car = new Object();
        bus = new Object();
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem(2);
    }

    @Test
    public void givenAVehicle_WhenPark_ShouldReturnTrue() {
        boolean parkVehicle = parkingLotSystem.park(new Object());
        Assert.assertTrue(parkVehicle);

    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldInformOwner() {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(car);
            parkingLotSystem.park(bus);
            Assert.assertEquals("Parking is full", Owner.ParkingLotInformation);
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldInformAirportSecurity() {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(car);
        parkingLotSystem.park(bus);
        Assert.assertEquals("Parking is full",AirportSecurity.parkingLotInformation);
    }

    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(car);
        boolean unParked = parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(unParked);
    }

    @Test
    public void givenVehicle_WhenUnParkedWhichIsNotParked_ShouldReturnFalse() {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(car);
        boolean unParked = parkingLotSystem.unPark(bus);
        Assert.assertFalse(unParked);
    }
}
