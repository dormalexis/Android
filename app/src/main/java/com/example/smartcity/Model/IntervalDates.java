package com.example.smartcity.Model;

import java.util.Calendar;
import java.util.Date;

public class IntervalDates {

    Long startDate;
    Long endDate;


    public IntervalDates(Date startDate, Date endDate)
    {
        this.startDate = dateToLong(startDate);
        this.endDate = dateToLong(endDate);
    }


    public boolean isInInterval(Long dateIn)
    {
        return dateIn >= startDate && dateIn <= endDate;

    }

    public Long dateToLong(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis() + 7200000;
    }
}
