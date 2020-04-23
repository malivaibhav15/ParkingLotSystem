package com.bridgelabz.Observer;

import java.util.HashMap;

public interface Observer {
    void sendParkingStatus(HashMap<Integer, String> parkingLot);
}
