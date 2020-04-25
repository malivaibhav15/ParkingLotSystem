package com.bridgelabzs;

public interface ParkingLotInformer {
    void addObserver(ParkingLotObserver lotObserver);

    void notifyParkingStatus(boolean parkingStatus);
}
