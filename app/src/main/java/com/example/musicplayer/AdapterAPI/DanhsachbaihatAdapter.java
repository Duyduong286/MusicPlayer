package com.example.musicplayer.AdapterAPI;

import android.annotation.SuppressLint;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Baihat> mangBaihat;
    private ArrayList<Songs> arrSong;
    private final StorageUtil storage;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangBaihat) {
        this.context = context;
        this.mangBaihat = mangBaihat;
        this.arrSong = new ArrayList<>();



        for (Baihat song: mangBaihat) {
            arrSong.add(song.getSong());
        }

        storage = new StorageUtil(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Baihat baihat = mangBaihat.get(i);
        holder.txtCasi.setText(baihat.getSinger());
        holder.txtBaihat.setText(baihat.getNameSong());
        holder.txtIndex.setText(i + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mangBaihat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtCasi, txtBaihat;
        ImageView imgThich;
        boolean liked;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            liked = true;
            txtIndex = itemView.findViewById(R.id.txtDanhSachIndex);
            txtCasi = itemView.findViewById(R.id.txtTenCaSi);
            txtBaihat = itemView.findViewById(R.id.txtTenBaihat);
            imgThich = itemView.findViewById(R.id.img_luot_thich_bai_hat);

            imgThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String temp = liked ? "1" : "-1";
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", mangBaihat.get(getPosition()).getIdSong());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            String ketqua = response.body();
                            String str = liked ? "Liked!!!" : "UnLiked";
                            int chag = liked ? 1 : -1;
                            assert ketqua != null;
                            if (ketqua.equals("Success")) {
                                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

                                //update anh
                                if (liked) {
                                    imgThich.setImageResource(R.drawable.iconloved);
                                } else {
                                    imgThich.setImageResource(R.drawable.iconlove);
                                }

                                liked = !liked;
                            } else {
                                Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        }
                    });
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context, Activity_Play_Nhac.class);
//                    intent.putExtra("cakhuc", mangBaihat.get(getPosition()));
//                    context.startActivity(intent);
                    DataLoader.playAudio(getPosition(), arrSong, storage, context);
                }
            });
        }
    }
}