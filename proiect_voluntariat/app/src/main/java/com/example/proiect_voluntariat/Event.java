package com.example.proiect_voluntariat;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private String name;
    private String description;
    private int points;

    public Event(String name, String description, int points) {
        this.name = name;
        this.description = description;
        this.points = points;
    }

    protected Event(Parcel in) {
        name = in.readString();
        description = in.readString();
        points = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(points);
    }
}


