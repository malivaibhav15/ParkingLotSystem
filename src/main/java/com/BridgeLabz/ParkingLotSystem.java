package com.BridgeLabz;

public class ParkingLotSystem {
    private Object vehicle;

    public ParkingLotSystem() {
    }

    public boolean park(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle != null)
            throw new ParkingLotSystemException("Parking is full");
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
