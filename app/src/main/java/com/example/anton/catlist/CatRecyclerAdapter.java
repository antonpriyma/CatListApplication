package com.example.anton.catlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anton.catlist.models.Cat;
import com.example.anton.catlist.models.CatsList;

public class CatRecyclerAdapter extends RecyclerView.Adapter<CatViewHolder> {
    static private final int ITEM_CAT = R.layout.cat_view;
    private CatsList list;


    CatRecyclerAdapter(CatsList list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View catView = LayoutInflater.from(context).inflate(ITEM_CAT, parent, false);
        return new CatViewHolder(catView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = list.getList().get(position);
        CatViewHolder catViewHolder = ((CatViewHolder) holder);
        String name = cat.getName();
        Bitmap img = cat.getImg();
        Context context = holder.itemView.getContext();
        catViewHolder.textView.setText(name);
        if (img!=null) {
            catViewHolder.imageView.setImageBitmap(img);
        }else {
            catViewHolder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.default_image));
        }
    }

    @Override
    public int getItemCount() {
        return list.getList().size();
    }
}
