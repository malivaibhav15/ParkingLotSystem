package com.bridgelabzs;

public class ParkingAttendant {
    ParkingLotSystem parkingLotSystem;
    int character = 65;
    private int lot;

    //PARAMETRISED CONSTRUCTOR
    public ParkingAttendant(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    //METHOD TO PARK THE VEHICLE IN PARKING LOT
    public void parkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED, "Vehicle is already parked");
        }
        String key = (vehicle.vehicleSize == Vehicle.VehicleSize.LARGE) ? getSlotPositionWithLessNumberOfVehicleParked() : getParkingPosition();
        parkingLotSystem.vehicleMap.put(key, vehicle);
    }

    //METHOD TO UNPARK THE VEHICLE FROM PARKING LOT
    public void unParkedVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (parkingLotSystem.vehicleMap.containsValue(vehicle))
            parkingLotSystem.vehicleMap.remove(getVehiclePosition(vehicle), vehicle);
        else
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NOT_PARKED_HERE, "VEHICLE_NOT_PARKED_HERE");
    }

    //METHOD TO GET THE POSITION IF VEHICLE IN PARKING LOT
    public String getVehiclePosition(Vehicle vehicle) {
        return parkingLotSystem.vehicleMap.keySet().stream()
                .filter(key -> vehicle.equals(parkingLotSystem.vehicleMap.get(key)))
                .findFirst()
                .get();
    }

    //METHOD TO GENERATE AND GET PARKING LOT POSITION
    public String getParkingPosition() {
        String position = null;
        int slotNumber = 0;
        while (slotNumber++ <= parkingLotSystem.SIZE_OF_PARKING_LOT) {
            char row = (char) (character + slotNumber);
            int flag = 0;
            for (int lotNumber = 1; lotNumber <= parkingLotSystem.NUMBER_OF_PARKING_LOTS; lotNumber++) {
                String key = lotNumber + "A" + row + " " + slotNumber;
                if (!parkingLotSystem.vehicleMap.containsKey(key)) {
                    position = key;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1)
                break;
        }
        return position;
    }

    //METHOD TO PARKING SLOT FOR LARGE SIZE VEHICLE
    public String getSlotPositionWithLessNumberOfVehicleParked() throws ParkingLotSystemException {
        int count = 0;
        int character = 65;
        while (count++ < parkingLotSystem.NUMBER_OF_PARKING_LOTS) {
            int numberOfVehicles = parkingLotSystem.getNumberOfVehiclesParked(count);
            char row = (char) (character + numberOfVehicles);
            if (parkingLotSystem.getNumberOfVehiclesParked(count) < (parkingLotSystem.SIZE_OF_PARKING_LOT - 1)) {
                return count+ "A" + row + " " + (parkingLotSystem.getNumberOfVehiclesParked(count) + 1);
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Large Vehicles Can't Be Parked");
    }

}
