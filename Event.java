package ius.iustudent.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private String name;
    private int startTime;
    private int endTime;
    private int day;
    private boolean empty = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        empty = false;
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        empty = false;
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        empty = false;
        this.endTime = endTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        empty = false;
        this.day = day;
    }

    public boolean isEmpty(){
        return empty;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", day=" + day +
                ", empty=" + empty +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.startTime);
        dest.writeInt(this.endTime);
        dest.writeInt(this.day);
        dest.writeByte(this.empty ? (byte) 1 : (byte) 0);
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.name = in.readString();
        this.startTime = in.readInt();
        this.endTime = in.readInt();
        this.day = in.readInt();
        this.empty = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
