package com.bridgelabzs;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ParkingDateTime {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime dateTime = LocalDateTime.now();

    //METHOD TO GET DATE AND TIME
    public LocalDateTime getDateTime() {
        String date = formatter.format(dateTime);
        return LocalDateTime.parse(date, formatter);
    }
}
