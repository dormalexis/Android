package com.example.smartcity.Utilitaries;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatesUtilitaries {

    public static String FormattedDate(Date date) {

        Locale locale = new Locale("fr", "BE");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        return dateFormat.format(date);
    }


    public static Integer DaysBetween(Date start, Date end)
    {
        Integer numberOfDays = 0;
        Date iterator = start;
        Calendar c = Calendar.getInstance();

        while(iterator.compareTo(end) <= 0)
        {
            numberOfDays++;

            c.setTime(iterator);
            c.add(Calendar.DAY_OF_MONTH, 1);
            iterator = c.getTime();
        }
        return numberOfDays;
    }

    public static Boolean isPastDate(Long milliseconds)
    {
        Date date = new Date();
        date.setTime(milliseconds);

        return date.compareTo(new Date()) < 0;
    }

}
