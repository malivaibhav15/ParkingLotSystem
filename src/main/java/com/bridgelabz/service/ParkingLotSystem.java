package com.bridgelabz;

import com.bridgelabz.exception.ParkingLotException;
import com.bridgelabz.observer.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingLotSystem implements Subject
{
    ArrayList<ParkingLotException.Observer> observers = new ArrayList<ParkingLotException.Observer>();
    ParkingAttendant attendant;
    ParkingBill parkingBill = new ParkingBill();
    int counter = 0;
    ParkingLot parkingLot;
    int lotCapacity;
    int lotSize;
    HashMap<Integer, HashMap> lotMaps = new HashMap<Integer, HashMap>();

    public ParkingLotSystem(int lotCapacity, int lotSize) {
        this.lotSize = lotSize;
        this.lotCapacity = lotCapacity;
        parkingLot = new ParkingLot(lotCapacity);
        for (int i = 1; i <= lotSize; i++) {
            HashMap<Integer, Object> map = parkingLot.getEmptyParkingLot();
            lotMaps.put(i, map);
        }
        attendant = new ParkingAttendant();
    }

    public void register(ParkingLotException.Observer obj) {
        observers.add(obj);
    }

    @Override
    public void notifyObservers() {
        for (Iterator<ParkingLotException.Observer> it =
             observers.iterator(); it.hasNext(); ) {
            ParkingLotException.Observer o = it.next();
            o.sendParkingMessage(counter, this.lotCapacity);
        }
    }

    public void parkVehicle(Object vehicle, int arrivingHour) throws ParkingLotException {
        if (counter >= lotCapacity * lotSize)
            throw new ParkingLotException("Parking lot is full.",
                    ParkingLotException.ExceptionType.NO_PARKING_AVAILABLE);
        AtomicBoolean vehicleCheck = new AtomicBoolean(false);
        lotMaps.values().stream().forEach(hashMap -> {
            if (hashMap.containsValue(vehicle)) {
                vehicleCheck.set(true);
            }
        });
        if (vehicleCheck.get())
            throw new ParkingLotException("Vehicle id already present",
                    ParkingLotException.ExceptionType.VEHICLE_ALREADY_PRESENT);
        lotMaps = attendant.parkVehicle(vehicle, lotMaps);
        counter++;
        parkingBill.arrivingHour(arrivingHour);
        this.notifyObservers();
    }

    public boolean isVehicleParked(Object vehicle) {
        for (HashMap map : lotMaps.values()) {
            if (map.containsValue(vehicle))
                return true;
        }
        return false;
    }

    public boolean unParkVehicle(Object vehicle, Integer parkingSlot, Integer parkingLotNumber, int departingHour) {
        if (lotMaps.get(parkingLotNumber).containsValue(vehicle)) {
            lotMaps.get(parkingLotNumber).put(parkingSlot, null);
            counter--;
            parkingBill.departureHour(departingHour);
            this.notifyObservers();
            return true;
        }
        return false;
    }

}
