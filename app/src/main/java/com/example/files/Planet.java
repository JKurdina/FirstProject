package com.example.files;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Planet implements Serializable {
    public String name;
    public float diameter_km;
    public float distance_from_sun_mkm;
    public int moons;
    public float day_length_hours;
    public String fact;

    public Planet(String name, float diameter, float distance, int moons, float day_length, String fact) {
        this.name = name;
        this.diameter_km = diameter;
        this.distance_from_sun_mkm = distance;
        this.moons = moons;
        this.day_length_hours = day_length;
        this.fact = fact;
    }

    public String getName() {return name;}
    public float getDiameter() {return diameter_km;}
    public float getDistanceFromSun() {return distance_from_sun_mkm;}
    public int getMoons() {return moons;}
    public float getDayLength() {return day_length_hours;}
    public String getFact() {return fact;}

}
