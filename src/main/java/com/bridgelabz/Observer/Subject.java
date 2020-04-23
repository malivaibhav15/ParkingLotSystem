package com.bridgelabz.Observer;

public interface Subject {
    void register(Observer o);

    void unRegister(Observer o);

    void notifyObservers();
}
