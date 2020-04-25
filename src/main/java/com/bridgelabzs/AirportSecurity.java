package com.bridgelabzs;

public class AirportSecurity implements ParkingLotObserver {

    private Boolean isParkingFull;

    @Override
    public void updateParkingStatus(boolean parkingStatus) {
        isParkingFull = parkingStatus;
    }
}
