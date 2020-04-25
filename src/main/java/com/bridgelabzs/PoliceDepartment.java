package com.bridgelabzs;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class PoliceDepartment {
    public ParkingLotSystem parkingLotSystem;
    public Map<String, Vehicle> vehicles;

    //CONSTRUCTOR
    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    //METHOD TO GET VEHICLES OF PARTICULAR COLOUR AND RETUNING MAP
    public Map<String, Vehicle> getVehicles(Vehicle.VehicleColour colour) {
        return vehicles = parkingLotSystem.vehicleMap.entrySet().stream()
                .filter(entry -> colour.equals(entry.getValue().vehicleColour))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //METHOD TO GET VEHICLES OF PARTICULAR COLOUR AND MODEL
    public Map<String, Vehicle> getVehiclesWithColorAndModel(Vehicle.VehicleColour vehicleColour, Vehicle.VehicleModel vehicleModel) {
        return vehicles = parkingLotSystem.vehicleMap.entrySet().stream()
                .filter(entry -> vehicleColour.equals(entry.getValue().vehicleColour) &&
                        vehicleModel.equals(entry.getValue().vehicleModel))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //METHOD TO GET VEHICLES OF PARTICULAR MODEL
    public Map<String, Vehicle> getVehiclesOfModel(Vehicle.VehicleModel vehicleModel) {
        return vehicles = parkingLotSystem.vehicleMap.entrySet().stream()
                .filter(entry -> vehicleModel.equals(entry.getValue().vehicleModel))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //METHOD TO GET VEHICLES FROM LAST MINUTES
    public Map<String, Vehicle> getVehiclesParkedFromLastMinutes(int minutes) {
        return vehicles = parkingLotSystem.vehicleMap.entrySet().stream()
                .filter(entry -> entry.getValue().parkingDateAndTime.getMinute() - LocalDateTime.now().getMinute() <= minutes)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //METHOD TO GET ALL VEHICLES OF PARKING LOT
    public Map<String, Vehicle> getAllParkedVehicles() {
        return vehicles = parkingLotSystem.vehicleMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    //METHOD TO GET VEHICLES BY ROW
    public Map<String, Vehicle> getVehiclesByRow(Vehicle.DriverType driverType, Vehicle.VehicleSize vehicleSize, char parkingRow) {
        return vehicles = parkingLotSystem.vehicleMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains("A" + parkingRow))
                .filter(entry -> driverType.equals(entry.getValue().driverType))
                .filter(entry -> vehicleSize.equals(entry.getValue().vehicleSize))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
