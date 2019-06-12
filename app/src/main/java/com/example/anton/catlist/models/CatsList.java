package com.example.anton.catlist.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.LruCache;

import java.io.InputStream;
import java.util.ArrayList;

public class CatsList implements Parcelable {
    public static final Parcelable.Creator<CatsList> CREATOR = new Parcelable.Creator<CatsList>() {
        @Override
        public CatsList createFromParcel(Parcel source) {
            return new CatsList(source);
        }

        @Override
        public CatsList[] newArray(int size) {
            return new CatsList[size];
        }
    };
    private static final String[] URLs = {"http://pngimg.com/uploads/cat/cat_PNG50546.png", "http://pngimg.com/uploads/cat/cat_PNG50537.png", "http://pngimg.com/uploads/cat/cat_PNG50525.png", "http://pngimg.com/uploads/cat/cat_PNG50511.png", "http://pngimg.com/uploads/cat/cat_PNG50498.png", "http://pngimg.com/uploads/cat/cat_PNG50480.png", "http://pngimg.com/uploads/cat/cat_PNG50433.png", "http://pngimg.com/uploads/cat/cat_PNG50425.png", "http://pngimg.com/uploads/cat/cat_PNG120.png", "http://pngimg.com/uploads/cat/cat_PNG104.png"};
    private static final String[] names = {"Барсик", "Кэсис", "Ферруцио", "Клементе", "Грампи", "Юппи", "Шелли", "Кактус", "Леопардо", "Жуля",};
    private static ArrayList<Cat> list;
    private final int CAT_NUMBER = 10;
    private LruCache<String, Bitmap> memoryCache;

    public CatsList() {
        list = new ArrayList<>();
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory/8 ;
        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return (bitmap.getRowBytes() * bitmap.getHeight())/1024;
            }
        };
    }

    protected CatsList(Parcel in) {
        this.list = new ArrayList<Cat>();
        in.readList(this.list, Cat.class.getClassLoader());
    }

    public ArrayList<Cat> getList() {
        return list;
    }

    public void setList(ArrayList<Cat> list) {
        this.list = list;
    }

    public void getWebCats(LoadCatsCallback callback) {
        for (int i = 0; i < CAT_NUMBER; i++) {
            list.add(new Cat(names[i]));
        }
        callback.onLoad();
        for (int i = 0; i < CAT_NUMBER; i++) {
            Bitmap imageFromCache = getBitmapFromMemCache(names[i]);
            if (imageFromCache==null) {
                new DownloadImageTask(callback, i).execute(URLs[i]);
            }else {
                list.get(i).setImg(imageFromCache);
                callback.onLoad();
            }
        }


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CAT_NUMBER);
        dest.writeList(this.list);
    }

    public interface LoadCatsCallback {
        void onLoad();
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Void> {
        private final LoadCatsCallback callback;
        private final int number;

        public DownloadImageTask(LoadCatsCallback callback, int i) {
            this.callback = callback;
            number = i;
        }

        protected Void doInBackground(String... urls) {


            try {
                String urldisplay = urls[0];
                InputStream in = new java.net.URL(urldisplay).openStream();
                Bitmap catPhoto = null;
                catPhoto = BitmapFactory.decodeStream(in);
                list.get(number).setImg(catPhoto);
                addBitmapToMemoryCache(names[number],catPhoto);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            Log.v("AsyncTask", "Done");
            callback.onLoad();
        }
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }
}
