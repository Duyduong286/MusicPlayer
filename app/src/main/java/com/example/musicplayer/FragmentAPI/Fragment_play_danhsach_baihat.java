package com.example.musicplayer.FragmentAPI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.ActivityAPI.Activity_Search_API;
import com.example.musicplayer.AdapterAPI.PlayNhacAdapter;
import com.example.musicplayer.R;


public class Fragment_play_danhsach_baihat extends Fragment {

    RecyclerView recyclerView;
    View view;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsach_baihat, container, false);
        recyclerView = view.findViewById(R.id.recycler_play_baihat);

        if (Activity_Search_API.mangBaiHat.size() > 0) {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), Activity_Search_API.mangBaiHat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playNhacAdapter);
        }
        return view;
    }
}