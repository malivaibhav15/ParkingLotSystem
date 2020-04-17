package com.BridgeLabz;

public class ParkingLotSystemException extends Exception
{
    String message;

    public ParkingLotSystemException(String message) {
        this.message = message;
    }
}
