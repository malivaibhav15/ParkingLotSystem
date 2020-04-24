package com.bridgelabz;

import com.bridgelabz.exception.ParkingLotException;

public interface Subject
{
    public void register(ParkingLotException.Observer obj);
    public void notifyObservers();
}
