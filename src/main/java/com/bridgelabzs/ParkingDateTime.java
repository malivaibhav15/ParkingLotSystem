package com.bridgelabzs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingDateTime {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = new Date();

    //METHOD TO DATE AND TIME
    public String getDateTime() {
        return formatter.format(date);
    }
}
