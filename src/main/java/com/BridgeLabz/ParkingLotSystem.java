package com.BridgeLabz;

public class ParkingLotSystem {
    private Object vehicle;

    public ParkingLotSystem() {
    }

    public boolean park(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle == null) return false;
        if(this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
