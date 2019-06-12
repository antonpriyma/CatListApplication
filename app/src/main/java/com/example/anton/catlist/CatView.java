package com.example.anton.catlist;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

public class CatView extends CardView {
    public CatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.cat_view, this);
        ImageView imageView = findViewById(R.id.image);
        TextView textView = findViewById(R.id.caption);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CatView);
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.CatView_image));
        textView.setText(attributes.getString(R.styleable.CatView_text));
        attributes.recycle();

    }
}
