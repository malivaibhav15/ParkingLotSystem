package com.bridgelabz.Observer;

import com.bridgelabz.service.ParkingLotSystem;

import java.util.HashMap;

public class Owner implements Observer{
    public enum Flag {PARKING_IS_VACANT, PARKING_IS_FULL};

    Flag flag;

    public Flag getFlag() {
        return flag;
    }

    @Override
    public void sendParkingStatus(HashMap<Integer, String> parkingLot) {
        flag = (ParkingLotSystem.isParkingLotFull(parkingLot)) ? Flag.PARKING_IS_FULL : Flag.PARKING_IS_VACANT;
    }

    public Integer getEmptyParkingSlot(HashMap<Integer, String> parkingLot) {
        for (int i = 1; i <= parkingLot.size(); i++) {
            if (parkingLot.get(i) == null)
                return i;
        }
        return null;
    }
}
