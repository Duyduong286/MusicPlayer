package com.example.musicplayer.AdapterAPI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicplayer.databaseAPI.Playlist;
import com.example.musicplayer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAPIAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAPIAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder {
        TextView txtTen;
        ImageView imgBackground, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTen = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgBackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        assert playlist != null;
        Picasso.get().load(playlist.getImagePlaylist())
                .into(viewHolder.imgBackground);
        Picasso.get().load(playlist.getIconPlaylist())
                .into(viewHolder.imgPlaylist);

        viewHolder.txtTen.setText(playlist.getNamePlaylist());
        return convertView;
    }
}