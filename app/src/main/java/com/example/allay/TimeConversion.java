package com.example.allay;

public class TimeConversion {

    public long convertYearToMillis(int years){
        long yearInMillis = (years * 365 * 24 * 60 * 60 * 1000);
        return yearInMillis;
    }

    public long convertMonthsToMillis(int months){
        long yearInMillis = (months * 30 * 24 * 60 * 60 * 1000);
        return yearInMillis;
    }

    public long convertDaysToMillis(int days){
        long yearInMillis = (days * 24 * 60 * 60 * 1000);
        return yearInMillis;
    }

    public long convertHoursToMillis(int hours){
        long yearInMillis = (hours * 60 * 60 * 1000);
        return yearInMillis;
    }

    public long convertMinutesToMillis(int minutes){
        long yearInMillis = (minutes * 60 * 1000);
        return yearInMillis;
    }

    public long convertSecondsToMillis(int seconds){
        long yearInMillis = (seconds * 1000);
        return yearInMillis;
    }
}
