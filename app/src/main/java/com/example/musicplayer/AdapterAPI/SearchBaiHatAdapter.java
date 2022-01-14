package com.example.musicplayer.AdapterAPI;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.databaseAPI.Baihat;
import com.example.musicplayer.R;
import com.example.musicplayer.Service.APIService;
import com.example.musicplayer.Service.Dataservice;
import com.example.musicplayer.StorageUtil;
import com.example.musicplayer.database.DataLoader;
import com.example.musicplayer.database.Songs;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> mangbaihat;
    private ArrayList<Songs> arrSong;
    private final StorageUtil storage;

    public SearchBaiHatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;

        this.arrSong = new ArrayList<>();



        for (Baihat song: mangbaihat) {
            arrSong.add(song.getSong());
        }

        storage = new StorageUtil(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view  = inflater.inflate(R.layout.dong_search_baihat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = mangbaihat.get(i);
        viewHolder.txtTenbaihat.setText(baihat.getNameSong());
        viewHolder.txtCasi.setText(baihat.getSinger());
        viewHolder.txtSoluotthich.setText(baihat.getLike());
        Picasso.get().load(baihat.getImagesong()).into(viewHolder.imgBaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenbaihat, txtCasi, txtSoluotthich;
        ImageView imgBaihat, imgLuotthich;
        boolean liked;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            liked = true;
            txtTenbaihat = itemView.findViewById(R.id.txt_search_tenbaihat);
            txtCasi = itemView.findViewById(R.id.txt_search_tencasi);
            txtSoluotthich = itemView.findViewById(R.id.txt_search_so_luot_thich);
            imgBaihat = itemView.findViewById(R.id.img_search);
            imgLuotthich = itemView.findViewById(R.id.img_search_luotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataLoader.playAudio(getPosition(), arrSong, storage, context);
                }
            });
            imgLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String temp = liked ? "1" : "-1";
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", mangbaihat.get(getPosition()).getIdSong());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            String str = liked ? "Liked!!!" : "UnLiked";
                            int chag = liked ? 1 : -1;
                            assert ketqua != null;
                            if (ketqua.equals("Success")) {
                                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

                                //update txt
                                DecimalFormat decimalFormat = new DecimalFormat("00");
                                int num = Integer.parseInt((String) txtSoluotthich.getText());
                                num += chag;
                                txtSoluotthich.setText(decimalFormat.format(num));

                                //update anh
                                if (liked) {
                                    imgLuotthich.setImageResource(R.drawable.iconloved);
                                } else {
                                    imgLuotthich.setImageResource(R.drawable.iconlove);
                                }

                                liked = !liked;
                            } else {
                                Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
}