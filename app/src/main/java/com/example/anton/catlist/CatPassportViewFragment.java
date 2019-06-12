package com.example.anton.catlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anton.catlist.presenters.CatPassportPresenter;
import com.example.anton.catlist.views.CatPassportViewFragmentI;

public class CatPassportViewFragment extends Fragment implements CatPassportViewFragmentI {
    private static final String CAT_NAME = "Cat name";
    private static final String CAT_PHOTO = "Cat photo";
    private CatPassportPresenter presenter;
    private ImageView passportPhoto;
    private TextView passportName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.cat_card_passport_fragment, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View view = getView();
        passportName = getView().findViewById(R.id.cat_passport_name);
        passportPhoto = getView().findViewById(R.id.cat_passport_photo);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String name = bundle.getString(CAT_NAME, "NoNameCat");
            Bitmap photo = parseDrawable(bundle.getByteArray(CAT_PHOTO));
            passportName.setText(name);
            passportPhoto.setImageBitmap(photo);
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        //presenter.setView(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        //presenter.unSetView();
    }


    private Bitmap parseDrawable(byte[] b) {
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        return bmp;
    }
}
