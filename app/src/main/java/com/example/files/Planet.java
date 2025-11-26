package com.example.files;

import android.os.Parcel;
import android.os.Parcelable;
public class Planet implements Parcelable {
    public String name;
    public float diameter_km;
    public float distance_from_sun_mkm;
    public int moons;
    public float day_length_hours;
    public String fact;

    public Planet() {}

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

    protected Planet(Parcel in) {
        name = in.readString();
        diameter_km = in.readFloat();
        distance_from_sun_mkm = in.readFloat();
        moons = in.readInt();
        day_length_hours = in.readFloat();
        fact = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(diameter_km);
        dest.writeFloat(distance_from_sun_mkm);
        dest.writeInt(moons);
        dest.writeFloat(day_length_hours);
        dest.writeString(fact);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Planet> CREATOR = new Creator<Planet>() {
        @Override
        public Planet createFromParcel(Parcel in) {
            return new Planet(in);
        }

        @Override
        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };

}
