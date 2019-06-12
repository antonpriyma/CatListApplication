package com.example.anton.catlist.views;

import android.graphics.drawable.Drawable;

public interface CatViewActivityI {
    void openCatListViewFragment();

    void openCatPassportFragment(String name, Drawable img);

}
