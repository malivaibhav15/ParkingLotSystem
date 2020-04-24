package com.bridgelabz.exception;

public class ParkingLotException extends Throwable {
    public enum ExceptionType {
        VEHICLE_ALREADY_PRESENT,
        NO_PARKING_AVAILABLE
    }

    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public static interface Observer
    {
        public void sendParkingMessage(int currentVehicleCount, int lotCapacity);
    }
}