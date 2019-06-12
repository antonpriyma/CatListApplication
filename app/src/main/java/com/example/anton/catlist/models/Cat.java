package com.example.anton.catlist.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

public class Cat implements Parcelable {
    public static final Parcelable.Creator<Cat> CREATOR = new Parcelable.Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel source) {
            return new Cat(source);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };
    private String name;
    private Bitmap img;

    public Cat(String name, Bitmap img) {
        this.name = name;
        this.img = img;
    }

    public Cat(String s) {
        this.name = s;
        this.img = BitmapFactory.decodeFile("img/default_image.jpg");
    }

    protected Cat(Parcel in) {
        this.name = in.readString();
        this.img = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.img, flags);
    }
}
