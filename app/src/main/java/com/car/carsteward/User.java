package com.car.carsteward;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.carsteward
 * @time 2019/10/27 21:01
 * @description
 */
public class User implements Parcelable {
    public int sex;
    public String name;


    protected User(Parcel in) {
        sex = in.readInt();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sex);
        dest.writeString(name);
    }
}
