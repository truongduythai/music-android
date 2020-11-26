package com.blacpythoz.musik.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blacpythoz.musik.R;
import com.blacpythoz.musik.activities.MusicServiceActivity;
import com.blacpythoz.musik.adapters.FavouriteAdapter;
import com.blacpythoz.musik.models.SongModel;
import com.blacpythoz.musik.services.MusicService;

import java.util.List;

/**
 * Created by deadsec on 9/3/17.
 */

public class FavouriteListFragment extends MusicServiceFragment {

    private RecyclerView recyclerView;
    private FavouriteAdapter favouriteAdapter;
    private List<SongModel> songs;
    private MusicService musicSrv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_list, container, false);
        recyclerView = view.findViewById(R.id.rv_favourite_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onServiceConnected(MusicService musicService) {
        musicSrv = musicService;
    }

    @Override
    public void onServiceDisconnected() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();
    }

    public void initFragment() {
        if (getActivity() instanceof MusicServiceActivity) {
            songs = ((MusicServiceActivity) getActivity()).getAllFavouriteSongs();
            favouriteAdapter = new FavouriteAdapter(songs);
            favouriteAdapter.setSongItemClickListener(new FavouriteAdapter.SongItemClickListener() {
                @Override
                public void onSongItemClick(View v, SongModel song, final int pos) {
                    Log.d(TAG,song.getTitle());
                    playSong(song);
                }
            });
            recyclerView.setAdapter(favouriteAdapter);
        }
    }

    public void playSong(SongModel song) {
        musicSrv.play(song);
    }

}
