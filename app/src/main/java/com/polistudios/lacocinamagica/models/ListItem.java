package com.polistudios.lacocinamagica.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class ListItem implements Parcelable {
    public String value;
    public boolean checked;

    protected ListItem(Parcel in) {
        value = in.readString();
        checked = in.readByte() != 0;
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeByte((byte) (checked ? 1 : 0));
    }
}