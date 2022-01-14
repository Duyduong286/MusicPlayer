package com.example.musicplayer.ActivityAPI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.AdapterAPI.SearchBaiHatAdapter;
import com.example.musicplayer.databaseAPI.Baihat;
import com.example.musicplayer.R;
import com.example.musicplayer.Service.APIService;
import com.example.musicplayer.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Search_API extends AppCompatActivity {

    public static ArrayList<Baihat> mangBaiHat;

    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView txt_khong_du_lieu;
    SearchBaiHatAdapter searchBaiHatAdapter;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mangBaiHat = new ArrayList<>();
        setContentView(R.layout.activity_search_api);
        toolbar = findViewById(R.id.search_toolbar_api);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setTitle("");

        recyclerView = findViewById(R.id.recycler_search_api);
        txt_khong_du_lieu = findViewById(R.id.txt_khongcodulieu);
        searchView = findViewById(R.id.search_view_api);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                SearchTuKhoaBaiHat(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    private void SearchTuKhoaBaiHat(String query) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.SearchBaiHat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                ArrayList<Baihat> mangbaihat = (ArrayList<Baihat>) response.body();
                if (mangbaihat.size() > 0) {
                    searchBaiHatAdapter = new SearchBaiHatAdapter(Activity_Search_API.this, mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Activity_Search_API.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(searchBaiHatAdapter);
                    txt_khong_du_lieu.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    txt_khong_du_lieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}