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
    public void givenAVehicle_WhenPark_ShouldReturnTrue() throws ParkingLotSystemException {
        boolean parkVehicle = parkingLotSystem.park(new Object());
        Assert.assertTrue(parkVehicle);

    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldInformOwner() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(car);
            parkingLotSystem.park(bus);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking is full", Owner.ParkingLotInformation);
        }
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldInformAirportSecurity() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(car);
            parkingLotSystem.park(bus);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking is full", AirportSecurity.parkingLotInformation);
        }
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(car);
            parkingLotSystem.park(bus);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking is full", e.message);
        }
    }

    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(car);
        boolean unParked = parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(unParked);
    }

    @Test
    public void givenVehicle_WhenUnParkedWhichIsNotParked_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(car);
        boolean unParked = parkingLotSystem.unPark(bus);
        Assert.assertFalse(unParked);
    }

    @Test
    public void givenVehicle_WhenUnPark_ShouldInformOwner() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(car);
            parkingLotSystem.unPark(vehicle);
            parkingLotSystem.unPark(car);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking lot is empty", Owner.ParkingLotInformation);
        }
    }

    @Test
    public void givenVehicle_WhenUnParkedAllVehicle_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(car);
            parkingLotSystem.unPark(vehicle);
            parkingLotSystem.unPark(car);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals("Parking lot is empty", e.message);
        }
    }
}
