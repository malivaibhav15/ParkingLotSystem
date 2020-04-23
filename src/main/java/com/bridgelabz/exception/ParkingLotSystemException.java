package com.bridgelabz.exception;

public class ParkingLotSystemException extends Exception
{
    public enum ExceptionType {
        PARKING_LOT_FULL, VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE;
    }
    public ExceptionType type;

    public ParkingLotSystemException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
