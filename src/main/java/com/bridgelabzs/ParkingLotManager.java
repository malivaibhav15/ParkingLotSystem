package com.bridgelabzs;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager implements ParkingLotInformer {
    private List<ParkingLotObserver> lotObservers;

    public ParkingLotManager() {
        lotObservers = new ArrayList<>();
    }

    @Override
    public void addObserver(ParkingLotObserver lotObserver) {
        lotObservers.add(lotObserver);
    }

    @Override
    public void notifyParkingStatus(boolean parkingStatus) {
        for (ParkingLotObserver lotObserver : lotObservers) {
            lotObserver.updateParkingStatus(parkingStatus);
        }
    }
}
