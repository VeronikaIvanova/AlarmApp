package com.example.acer_pc.alarmapp.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by acer-pc on 08.04.2017.
 */

public class Alarm implements Serializable{
    private int year, month, day, hour, minute;
    private Boolean isPeriod;
    private Melody melody;
    private Calendar calendar;

    public Boolean getTurnedOn() {
        return isTurnedOn;
    }

    public void setTurnedOn(Boolean turnedOn) {
        isTurnedOn = turnedOn;
        if (turnedOn){
            calendar=Calendar.getInstance();
            if (hour < calendar.get(Calendar.HOUR_OF_DAY) )
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            else
            if (hour == calendar.get(Calendar.HOUR_OF_DAY) && minute < calendar.get(Calendar.MINUTE))
                calendar.add(Calendar.DAY_OF_MONTH, 1);

            this.year = calendar.get(Calendar.YEAR);
            this.month = calendar.get(Calendar.MONTH);;
            this.day =  calendar.get(Calendar.DAY_OF_MONTH);
        }
    }

    private  Boolean isTurnedOn;


    private int dateOfCreation;

    public  Alarm(){
        setTurnedOn(true);
        calendar=Calendar.getInstance();
        dateOfCreation=calendar.get(Calendar.MILLISECOND);
    }

    public Alarm(int hour, int minute, Boolean isPeriod, Melody melody) {
        calendar=Calendar.getInstance();
        dateOfCreation=calendar.get(Calendar.MILLISECOND);
        setTurnedOn(true);
        this.hour = hour;
        this.minute = minute;
        this.isPeriod = isPeriod;
        this.melody = melody;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Boolean getPeriod() {
        return isPeriod;
    }

    public void setPeriod(Boolean period) {
        isPeriod = period;
    }

    public Melody getMelody() {
        return melody;
    }

    public void setMelody(Melody melody) {
        this.melody = melody;
    }

    public int getDateOfCreation() {
        return dateOfCreation;
    }
    @Override
    public String toString() {
        return "Alarm{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", isPeriod=" + isPeriod +
                ", melody=" + melody +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alarm alarm = (Alarm) o;

        if (year != alarm.year) return false;
        if (month != alarm.month) return false;
        if (day != alarm.day) return false;
        if (hour != alarm.hour) return false;
        if (minute != alarm.minute) return false;
        if (dateOfCreation!= alarm.dateOfCreation) return  false;
        return true;// melody == alarm.melody;

    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        result = 31 * result + day;
        result = 31 * result + hour;
        result = 31 * result + minute;
        result = 31 * result + dateOfCreation;
        return result;
    }
}
