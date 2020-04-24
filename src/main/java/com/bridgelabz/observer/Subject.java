package com.bridgelabz.observer;

import com.bridgelabz.exception.ParkingLotException;

public interface Subject
{
    public void register(ParkingLotException.Observer obj);
    public void notifyObservers();
}
