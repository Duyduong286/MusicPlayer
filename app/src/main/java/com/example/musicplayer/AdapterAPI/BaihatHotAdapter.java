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

public class BaihatHotAdapter extends RecyclerView.Adapter<BaihatHotAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Baihat> baihatArrayList;
    private ArrayList<Songs> arrSong;

    private final StorageUtil storage;

    public BaihatHotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
        this.arrSong = new ArrayList<>();

        for (Baihat song: baihatArrayList) {
            arrSong.add(song.getSong());
        }

        storage = new StorageUtil(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihathot, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = baihatArrayList.get(i);
        viewHolder.txtCasi.setText(baihat.getSinger());
        viewHolder.txtTen.setText(baihat.getNameSong());

        Picasso.get().load(baihat.getImagesong()).into(viewHolder.imgHinh);

        DecimalFormat decimalFormat = new DecimalFormat("00");
        int a = Integer.parseInt(baihat.getLike());
        viewHolder.txtSoluotthich.setText(decimalFormat.format(a));
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTen, txtCasi, txtSoluotthich;
        ImageView imgHinh, imgLuotthich;
        boolean liked;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            liked = true;
            txtTen = itemView.findViewById(R.id.txtTenBaihatHot);
            txtCasi = itemView.findViewById(R.id.txtCasiBaihatHot);
            txtSoluotthich = itemView.findViewById(R.id.txt_so_luot_thich);
            imgHinh = itemView.findViewById(R.id.imgBaihathot);
            imgLuotthich = itemView.findViewById(R.id.imgBaihathot_love);
            imgLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String temp = liked ? "1" : "-1";
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich(temp, baihatArrayList.get(getPosition()).getIdSong());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
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
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        }
                    });




                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context, Activity_Play_Nhac.class);
//                    intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
//                    context.startActivity(intent);
                      DataLoader.playAudio(getPosition(), arrSong, storage, context);
                }
            });
        }
    }
}