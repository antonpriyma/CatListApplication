package com.example.anton.catlist;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.anton.catlist.views.CatViewActivityI;

import java.io.ByteArrayOutputStream;

public class CatActivity extends AppCompatActivity implements CatViewActivityI {
    private static final String CAT_LIST_FRAGMENT = "Cat list";
    private static final String CAT_PASSPORT_FRAGMENT = "Cat passport";
    private static final String FRAGMENT_STATE = "Fragment State";
    private static final String CAT_ITEM = "Cat item";
    private static final String CAT_NAME = "Cat name";
    private static final String CAT_PHOTO = "Cat photo";
    private String activeFragment;
    FragmentTransaction fragmentTransaction;
    CatListViewFragment catListViewFragment;
    CatPassportViewFragment catPassportViewFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            catListViewFragment = new CatListViewFragment();
            catPassportViewFragment = new CatPassportViewFragment();
            openCatListViewFragment();
        } else {
            catListViewFragment = (CatListViewFragment) getSupportFragmentManager().getFragment(savedInstanceState, CAT_LIST_FRAGMENT);
            catPassportViewFragment=(CatPassportViewFragment)getSupportFragmentManager().getFragment(savedInstanceState,CAT_PASSPORT_FRAGMENT);
            activeFragment=savedInstanceState.getString(FRAGMENT_STATE);
        }

        if (catPassportViewFragment ==null){
            catPassportViewFragment = new CatPassportViewFragment();
        }
        if (catListViewFragment ==null){
            catListViewFragment = new CatListViewFragment();
        }

    }

    @Override
    public void openCatListViewFragment() {
        activeFragment=CAT_LIST_FRAGMENT;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, catListViewFragment);
        fragmentTransaction.commit();
    }


    public void openCatListViewFragmentFromPassport() {
        activeFragment=CAT_LIST_FRAGMENT;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left_open,R.animator.slide_in_right_close);
        fragmentTransaction.replace(R.id.fragment_container, catListViewFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void openCatPassportFragment(String name, Drawable img) {
        activeFragment=CAT_PASSPORT_FRAGMENT;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString(CAT_NAME, name);
        Bitmap bitmap = ((BitmapDrawable) img).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        args.putByteArray(CAT_PHOTO, b);
        catPassportViewFragment.setArguments(args);
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_in_right);
        fragmentTransaction.replace(R.id.fragment_container, catPassportViewFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (activeFragment.equals(CAT_LIST_FRAGMENT)){
            super.onBackPressed();
        }else {
            openCatListViewFragmentFromPassport();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (activeFragment.equals(CAT_LIST_FRAGMENT)) {
            getSupportFragmentManager().putFragment(outState, CAT_LIST_FRAGMENT, catListViewFragment);
        }else {
            getSupportFragmentManager().putFragment(outState, CAT_PASSPORT_FRAGMENT, catPassportViewFragment);
        }
        outState.putString(FRAGMENT_STATE,activeFragment);
    }


}
