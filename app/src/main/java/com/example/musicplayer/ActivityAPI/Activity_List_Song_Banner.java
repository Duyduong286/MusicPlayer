package com.example.musicplayer.ActivityAPI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.AdapterAPI.DanhsachbaihatAdapter;
import com.example.musicplayer.MediaPlayerService;
import com.example.musicplayer.databaseAPI.Album;
import com.example.musicplayer.databaseAPI.Baihat;
import com.example.musicplayer.databaseAPI.Playlist;
import com.example.musicplayer.databaseAPI.Quangcao;
import com.example.musicplayer.R;
import com.example.musicplayer.Service.APIService;
import com.example.musicplayer.Service.Dataservice;
import com.example.musicplayer.StorageUtil;
import com.example.musicplayer.database.DataLoader;
import com.example.musicplayer.database.Songs;
import com.example.musicplayer.nowplaying.NowPlaying;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_List_Song_Banner extends AppCompatActivity {

    Toast toast;
    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    ImageView imgdanhsachcakhuc;
    ArrayList<Baihat> mangBaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Playlist playlist;
    Album album;

    private ArrayList<Songs> arrSong;
    private  StorageUtil storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song_api);
        DataIntent();
        setID();
        init();

        if (quangcao != null && !quangcao.getNameSong().equals("")) {
            setValueInView(quangcao.getNameSong(), quangcao.getImageSong());
            GetDataQuangcao(quangcao.getIdAds());
        }

        if (playlist != null && !playlist.getNamePlaylist().equals("")) {
            setValueInView(playlist.getNamePlaylist(), playlist.getImagePlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }

        if (album != null && !album.getNameAlbum().equals("")) {
            setValueInView(album.getNameAlbum(), album.getImage());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void setActionButton() {

        this.arrSong = new ArrayList<>();

        for (Baihat song: mangBaihat) {
            arrSong.add(song.getSong());
        }

        storage = new StorageUtil(getApplicationContext());

        Button play_all = findViewById(R.id.play_all_api);
        Button shuffle_all = findViewById(R.id.shuffle_all_api);
        Button queue_all = findViewById(R.id.queue_all_api);

        play_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLoader.playAudio(0, arrSong, storage, getApplicationContext());
            }
        });

        shuffle_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLoader.playAudio(new Random().nextInt(arrSong.size()), arrSong, storage, getApplicationContext());
                NowPlaying.shuffle = true;
            }
        });
//
        queue_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (toast != null) toast.cancel();

                MediaPlayerService.audioList.addAll(arrSong);
                storage.storeAudio(MediaPlayerService.audioList);

                toast = Toast.makeText(getApplicationContext(), "QUEUED", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        setName();
    }

    public void setName() {
        TextView nameSong = findViewById(R.id.name_song_list_api);
        TextView nameAr = findViewById(R.id.name_ar_list_api);
        TextView numSong = findViewById(R.id.num_list_api);

        if (mangBaihat != null) {
            nameSong.setText(mangBaihat.get(0).getNameSong());
            nameAr.setText(mangBaihat.get(0).getSinger());
            numSong.setText(String.valueOf(mangBaihat.size()));
        }
    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoPlaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_Song_Banner.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_Song_Banner.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                setActionButton();
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });

    }

    private void GetDataQuangcao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_Song_Banner.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_Song_Banner.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                setActionButton();
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }



    private void setValueInView(String nameSong, String imageSong) {
        //collapsingToolbarLayout.setTitle(nameSong);
        /*try {
            URL url = new URL(imageSong);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Picasso.get().load(imageSong).into(imgdanhsachcakhuc);
    }

    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_Song_Banner.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_Song_Banner.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                setActionButton();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
//        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
//        floatingActionButton.setEnabled(false);
    }

    private void setID() {
        //coordinatorLayout          = findViewById(R.id.coordinatorLayout);
        //collapsingToolbarLayout    = findViewById(R.id.collasingToolbar);
        toolbar                    = findViewById(R.id.toolbarDanhsach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerDanhSachBaihat);
        //floatingActionButton       = findViewById(R.id.floatingActionButton);
        imgdanhsachcakhuc          = findViewById(R.id.imgdanhsachcakhuc);
    }


    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangcao.getNameSong(), Toast.LENGTH_SHORT).show();
            }
            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

}