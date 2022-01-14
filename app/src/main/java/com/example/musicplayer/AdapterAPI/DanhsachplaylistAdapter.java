package com.example.musicplayer.AdapterAPI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.ActivityAPI.Activity_List_Song_Banner;
import com.example.musicplayer.databaseAPI.Playlist;
import com.example.musicplayer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachplaylistAdapter extends RecyclerView.Adapter<DanhsachplaylistAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Playlist> mangPlaylist;

    public DanhsachplaylistAdapter(Context context, ArrayList<Playlist> mangPlaylist) {
        this.context = context;
        this.mangPlaylist = mangPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_playlist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Playlist playlist = mangPlaylist.get(i);
        Picasso.get().load(playlist.getIconPlaylist()).into(viewHolder.imgHinh);
        viewHolder.txtTen.setText(playlist.getNamePlaylist());
        //viewHolder.txtCasi.setText(playlist.getCasi());
    }

    @Override
    public int getItemCount() {
        return mangPlaylist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinh;
        TextView txtTen, txtCasi;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgDanhsachPlaylist);
            txtTen = itemView.findViewById(R.id.txtDanhSachPlaylist);
            //txtCasi = itemView.findViewById(R.id.txtCasiPlaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_List_Song_Banner.class);
                    intent.putExtra("itemplaylist", mangPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}