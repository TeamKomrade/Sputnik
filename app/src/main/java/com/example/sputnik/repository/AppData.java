package com.example.sputnik.repository;

import android.content.Context;
import android.widget.ImageView;

import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.sputnik.R;
import com.example.sputnik.repository.database.ContactDatabase;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class AppData {
    private  static AppData instance;
    private RequestManager glide;
    public static final String ID = "id";
    public  ContactDatabase contactDatabase;

    private AppData(Context context) {
        contactDatabase = Room.databaseBuilder(context,
                ContactDatabase.class, "database-name")
                .allowMainThreadQueries().build();
        glide = Glide.with(context);
    }

    public void loadImage(String url, ImageView imageView)
    {
        glide.load(url)
                .dontAnimate()
                .transform(new RoundedCornersTransformation(45, 0))
                .error(R.drawable.ic_baseline_error_24)
                .into(imageView);
    }

    public static AppData getAppDataInstance(Context context) {
        if(instance==null)
            instance = new AppData(context);
        return instance;
    }


}
