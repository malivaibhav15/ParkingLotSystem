package com.BridgeLabz;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    public int parkingCapacity;
    public List list = new ArrayList();
    private Object vehicle;

    public ParkingLotSystem(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    public boolean park(Object vehicle) {
        if (list.size() == this.parkingCapacity) {
            Owner.ParkingLotInformation = "Parking is full";
            AirportSecurity.parkingLotInformation = "Parking is full";
        } else {
            list.add(vehicle);
            return true;
        }
        return false;
    }

    public boolean unPark(Object vehicle) {
        if (list.contains(vehicle)) {
            list.remove(vehicle);
            if (list.size() == 0)
                Owner.ParkingLotInformation = "Parking lot is empty";
            return true;
        }
        return false;
    }
}
