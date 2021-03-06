package com.example.smartcity.Utilitaries;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckForms {

    public static Boolean isValidStartDate(Date startDate, Date endDate)
    {
        return startDate.compareTo(endDate) <= 0;
    }

    public static Boolean isValidName(String name)
    {
        Pattern patternName = Pattern.compile("^\\D*$");
        Matcher matcher = patternName.matcher(name);

        return !name.isEmpty() && name.length() <= 50 && matcher.find();

    }

    public static Boolean isValidItemName(String name)
    {
        return !name.isEmpty() && name.length() <= 100;
    }

    public static Boolean isValidPrice(String amount)
    {
        try {
            int price = Integer.parseInt(amount);
            return price > 0;
        }

        catch (NumberFormatException e)
        {
            return false;
        }

    }

    public static Boolean isValidStreetName(String streetName)
    {

        return !streetName.isEmpty() && streetName.length() <= 100;

    }

    public static Boolean isValidStreetNumber(String streetNumber)
    {
        try {
            Integer.parseInt(streetNumber);
            return true;
        }

        catch (NumberFormatException e)
        {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmail.matcher(email);
        return !email.isEmpty() && matcher.find();
    }

    public static Boolean isValidPhoneNumber(String phone)
    {
        Pattern patternPhone = Pattern.compile("(0|\\+32)\\d{8,9}");
        Matcher matcher = patternPhone.matcher(phone);
        return !phone.isEmpty() && phone.length() <= 12 && matcher.find();
    }


    public static Boolean isValidPassword(String password)
    {
        return password.length() >= 6;
    }

    public static Boolean isValidDescription(String description)
    {
        return !description.isEmpty() && description.length() <= 255;
    }

    public static Boolean isValidBox(String box)
    {
        return box.isEmpty() || box.length() <= 5;
    }



}
