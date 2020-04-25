package com.bridgelabzs;

public class ParkingLotSystemException extends Exception {
    public ExceptionType type;

    public ParkingLotSystemException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {NOT_PARKED_HERE, VEHICLE_ALREADY_PARKED, PARKING_LOT_IS_FULL}
}
