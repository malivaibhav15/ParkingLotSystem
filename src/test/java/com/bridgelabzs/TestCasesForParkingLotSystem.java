package com.bridgelabzs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class TestCasesForParkingLotSystem {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    ParkingLotOwner parkingLotOwner = null;
    AirportSecurity airportSecurity = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime dateTime = LocalDateTime.now();
    String formattedDateTime = formatter.format(dateTime);

    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem(2, 3);
        parkingLotOwner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicleToPark_WhenParkedInParkingLot_ShouldReturnTrue() throws ParkingLotSystemException {
        vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        parkingLotSystem.park(vehicle);
        boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertTrue(isPark);
    }

     @Test
    public void givenAVehicle_WhenNotParkedInParkingLot_ShouldReturnFalse() {
        vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertFalse(isPark);
    }

    @Test
    public void givenAVehicle_WhenUnParkedFromParkingLot_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.unPark(vehicleOne);
        boolean isUnPark = parkingLotSystem.isVehicleUnPark(vehicleOne);
        Assert.assertTrue(isUnPark);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParkedInParkingLot_ShouldThrowException() {
        parkingLotSystem = new ParkingLotSystem(3, 3);
        try {
            parkingLotSystem.park(new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA"));
            parkingLotSystem.park(new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "BBB"));
            parkingLotSystem.park(new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "CCC"));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenNoVehicleUnParked_ShouldThrowException() {
        try {
            Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
            Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "BBB");
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.unPark(vehicleTwo);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NOT_PARKED_HERE, e.type);
        }
    }

    @Test
    public void givenAVehicleWhenParked_SaysItIsUnParked_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        parkingLotSystem.park(vehicleOne);
        boolean isUnPark = parkingLotSystem.isVehicleUnPark(vehicleOne);
        Assert.assertFalse(isUnPark);
    }

    @Test
    public void givenAVehicle_WhenThereIsSpace1inParkingLot_ShouldAllowToPark() throws ParkingLotSystemException {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "BBB");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.park(vehicleThree);
        boolean isPark = parkingLotSystem.isVehicleParked(vehicleThree);
        Assert.assertTrue(isPark);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "AAA");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleOne);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAskForPosition_ShouldReturnPosition() throws ParkingLotSystemException {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        parkingLotSystem.park(vehicleOne);
        String vehiclePosition = parkingLotSystem.getVehiclePosition(vehicleOne);
        Assert.assertEquals("A1 1", vehiclePosition);
    }

    @Test
    public void givenAVehicle_WhenToPark_ShouldReturnParkingDateTime() throws ParkingLotSystemException {
        vehicle = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "AAA");
        parkingLotSystem.park(vehicle);
        LocalDateTime dateTime = LocalDateTime.parse(formattedDateTime, formatter);
        LocalDateTime parkingDateAndTime = vehicle.parkingDateAndTime;
        Assert.assertEquals(dateTime, parkingDateAndTime);
    }

    @Test
    public void givenAVehicles_WhenParkedInParkingLot_ShouldEvenlyDistributeInParkingLot() throws ParkingLotSystemException {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "BBB");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        String vehicle1Position = parkingLotSystem.getVehiclePosition(vehicleOne);
        String vehicle2Position = parkingLotSystem.getVehiclePosition(vehicleTwo);
        Assert.assertEquals("A1 1", vehicle1Position);
        Assert.assertEquals("A2 1", vehicle2Position);
    }

    @Test
    public void givenAVehicle_WhenDriverIsHandicap_ShouldParkVehicleAtNearestLotPosition() throws ParkingLotSystemException {

        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "AAA");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.unPark(vehicleOne);
        parkingLotSystem.park(vehicleThree);
        String vehicleThreePosition = parkingLotSystem.getVehiclePosition(vehicleThree);
        Assert.assertEquals("A1 1", vehicleThreePosition);
    }

    @Test
    public void givenVehicleOfLargeSize_WhenMaximumNumberOfParkingSlotAvailable_ShouldParkedInThatParkingLot() throws ParkingLotSystemException {
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "BBB");
        Vehicle vehicleFour = new Vehicle("4", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "AAA");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.park(vehicleThree);
        parkingLotSystem.park(vehicleFour);
        String vehicleFourPosition = parkingLotSystem.getVehiclePosition(vehicleFour);
        Assert.assertEquals("A2 2", vehicleFourPosition);
    }

    @Test
    public void givenParkingLotMap_NeedInformationAsPerColour_ShouldReturnVehicleMapWithRespectiveColour() throws ParkingLotSystemException {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "VVV");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "CCC");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.park(vehicleThree);
        Map<String, Vehicle> vehiclesOfColour = policeDepartment.getVehicles(Vehicle.VehicleColour.WHITE);
        Assert.assertEquals(3, vehiclesOfColour.size());
    }

    @Test
    public void givenParkingLot_WhenVehicleColorAndBrandMatch_ShouldReturnColorBlueAndModelToyota() throws ParkingLotSystemException {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.TOYOTA, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "BBB");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "CCC");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.park(vehicleThree);
        Map<String, Vehicle> vehiclesList = policeDepartment.getVehiclesWithColorAndModel(Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.TOYOTA);
        Assert.assertEquals(1, vehiclesList.size());
    }

    @Test
    public void givenParkingLot_WhenNeedVehicleOfParticularModel_ShouldReturnVehiclesInformation() throws ParkingLotSystemException {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "BBB");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.TOYOTA, "CCC");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.park(vehicleThree);
        Map<String, Vehicle> vehiclesOfParticularModel = policeDepartment.getVehiclesOfModel(Vehicle.VehicleModel.BMW);
        Assert.assertEquals(2, vehiclesOfParticularModel.size());
    }

    @Test
    public void givenParkingLot_WhenVehicleParkedInLastHalfHourMinutes_ShouldReturnVehiclesList() throws ParkingLotSystemException {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "BBB");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "BBB");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.park(vehicleThree);
        Map<String, Vehicle> vehiclesParkedInLastHalfHour = policeDepartment.getVehiclesParkedFromLastMinutes(30);
        Assert.assertEquals(3, vehiclesParkedInLastHalfHour.size());
    }

    @Test
    public void givenParkingLot_WhenNeedInformationOfAllVehicles_ShouldReturnVehiclesList() throws ParkingLotSystemException {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "AAA");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.park(vehicleThree);
        Map<String, Vehicle> allVehicles = policeDepartment.getAllParkedVehicles();
        Assert.assertEquals(3, allVehicles.size());
    }

    @Test
    public void givenVehicleTypeAndVehicleSize_WhenVehicleTypeAndSizeMatch_ShouldReturnVehiclesListOnRowB() throws ParkingLotSystemException {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle("1", Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleTwo = new Vehicle("2", Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleThree = new Vehicle("3", Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "AAA");
        Vehicle vehicleFour = new Vehicle("4", Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "AAA");
        parkingLotSystem.park(vehicleOne);
        parkingLotSystem.park(vehicleTwo);
        parkingLotSystem.park(vehicleThree);
        parkingLotSystem.park(vehicleFour);
        Map<String, Vehicle> vehiclesList = policeDepartment.getVehiclesByRow(Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, 'B');
        Assert.assertEquals(2, vehiclesList.size());
    }
}
