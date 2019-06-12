package com.example.anton.catlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CatViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;

    public CatViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.caption);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int itemPosition = getAdapterPosition();
                CatActivity catActivity = (CatActivity) view.getContext();
                catActivity.openCatPassportFragment(textView.getText().toString(), imageView.getDrawable());
            }
        });
    }
}
