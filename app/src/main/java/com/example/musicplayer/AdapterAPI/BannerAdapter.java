package com.example.musicplayer.AdapterAPI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.musicplayer.ActivityAPI.Activity_List_Song_Banner;
import com.example.musicplayer.databaseAPI.Quangcao;
import com.example.musicplayer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {

    Context context;
    private ArrayList<Quangcao> arrayListbanner;


    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imgbackground = view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgsong = view.findViewById(R.id.imageviewbanner);
        TextView txttitle = view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView txtnoidung = view.findViewById(R.id.textviewnoidung);

        Picasso
                .get()
                .load(arrayListbanner.get(position).getImageAds())
                .error(R.drawable.ic_launcher_foreground)
                .into(imgbackground);
        Picasso
                .get()
                .load(arrayListbanner.get(position).getImageSong())
                .error(R.drawable.ic_launcher_foreground)
                .into(imgsong);
        txttitle.setText(arrayListbanner.get(position).getNameSong());
        txtnoidung.setText(arrayListbanner.get(position).getContentAds());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Activity_List_Song_Banner.class);
                intent.putExtra("banner", arrayListbanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}