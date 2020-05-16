package com.example.covid_savar.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
    public static String getDateFormat(String milliSeconds)
    {

        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd.MM.yyyy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return formatter.format(calendar.getTime());
    }
}
