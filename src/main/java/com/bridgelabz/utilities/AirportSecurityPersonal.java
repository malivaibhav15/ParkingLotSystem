package com.bridgelabz.utilities;

import com.bridgelabz.exception.ParkingLotException;

public class AirportSecurityPersonal implements ParkingLotException.Observer
{
        boolean parkingIsFull;
        @Override
        public void sendParkingMessage(int currentVehicleCount, int lotCapacity) {
            if (currentVehicleCount >= lotCapacity)
                parkingIsFull=true;
        }

        public boolean redirectSecurityStaff() {
            return parkingIsFull;
        }

    }
