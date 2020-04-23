package com.bridgelabz;

import com.bridgelabz.Observer.AirportPersonnel;
import com.bridgelabz.Observer.Owner;
import com.bridgelabz.exception.ParkingLotSystemException;
import com.bridgelabz.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCasesForParkingLotSystem {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    Owner owner = null;
    AirportPersonnel airportPersonnel = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(5);
        vehicle = new Object();
        owner = new Owner();
        airportPersonnel = new AirportPersonnel();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park("Hyundai Aura");
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park("Hyundai Verna");
        boolean isVehicleUnParked = parkingLotSystem.unPark("Hyundai Verna");
        Assert.assertTrue(isVehicleUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {

        try {
            parkingLotSystem.park("Hyundai Santro");
            parkingLotSystem.unPark("Toyota Fortuner");
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            parkingLotSystem.park("Hyundai Grand i10");
            parkingLotSystem.park("Toyota Fortuner");
            parkingLotSystem.park("BMW X1");
            parkingLotSystem.park("BMW 3 Series GT");
            parkingLotSystem.park("tata harrier");
            parkingLotSystem.park("Hyundai Santro");
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park("Hyundai Verna");
            parkingLotSystem.unPark("Hyundai Verna");
            parkingLotSystem.unPark("Hyundai Verna");
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Hyundai Verna");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Hyundai Santro");
        parkingLotSystem.park("Tata Hexa");
        parkingLotSystem.park("Hyundai Grand i10");
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Hyundai Verna");
        parkingLotSystem.park("Hyundai Verna");
        parkingLotSystem.park("Tata Hexa");
        parkingLotSystem.park("Tata Hexa");
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffShouldBeUpdated() throws ParkingLotSystemException {
        parkingLotSystem.register(airportPersonnel);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        parkingLotSystem.park("Tata Hexa");
        parkingLotSystem.park("Hyundai Verna");
        Assert.assertEquals(true, airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotSystemException {
        parkingLotSystem.register(airportPersonnel);
        parkingLotSystem.park("Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Swift Dzire");
        Assert.assertFalse(airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsFull_IfItHasSpaceAgain_OwnerShouldShowVacantSign() throws ParkingLotSystemException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Tata Nexon");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        parkingLotSystem.park("Tata Altroz");
        parkingLotSystem.park("Tata Tiago");
        parkingLotSystem.unPark("Swift Dzire");
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenCar_IfFoundInParkingLot_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.park("Tata Indigo");
        parkingLotSystem.park("Tata Tiago");
        parkingLotSystem.park("Tata Harrier");
        boolean isPresent = parkingLotSystem.isVehiclePresentInLot("Toyota Fortuner");
        Assert.assertTrue(isPresent);
    }

    @Test
    public void givenCar_IfNotFoundInParkingLot_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park("Hyundai Verna");
        parkingLotSystem.park("Hyundai Grand i10");
        parkingLotSystem.park("Hyundai Aura");
        boolean isPresent = parkingLotSystem.isVehiclePresentInLot("Hyundai Santro");
        Assert.assertFalse(isPresent);
    }

}
