//package com.example.anton.catlist.models;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.Drawable;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import java.io.InputStream;
//
//public class DownloadImageTask extends AsyncTask<String, Void, Drawable> {
//    Drawable result;
//
//    public DownloadImageTask(Drawable bmImage) {
//        this.result = bmImage;
//    }
//
//    protected Bitmap doInBackground(String... urls) {
//        String urldisplay = urls[0];
//        Bitmap mIcon11 = null;
//        try {
//            InputStream in = new java.net.URL(urldisplay).openStream();
//            mIcon11 = BitmapFactory.decodeStream(in);
//        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
//            e.printStackTrace();
//        }
//        return mIcon11;
//    }
//
//    protected void onPostExecute(Bitmap result) {
//        bmImage.
//    }
//}