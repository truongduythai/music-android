package com.blacpythoz.musik.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blacpythoz.musik.R;
import com.blacpythoz.musik.models.SongModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    List<SongModel> songs;
    SongItemClickListener songItemClickListener;

    public FavouriteAdapter(List<SongModel> songs) {
        this.songs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SongModel song = songs.get(position);
        holder.tvSongName.setText(song.getTitle());
        holder.tvArtistName.setText(song.getArtistName());

        if (!song.getAlbumArt().equals("")) {
            Picasso.with(holder.itemView.getContext()).load(song.getAlbumArt()).placeholder(R.drawable.default_song_art).into(holder.ivSongCoverArt);
        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songItemClickListener != null) {
                    songItemClickListener.onSongItemClick(view, song, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;
        TextView tvSongName;
        TextView tvArtistName;
        ImageView ivSongCoverArt;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSongName = itemView.findViewById(R.id.tv_song_name);
            tvArtistName = itemView.findViewById(R.id.tv_artist_name);
            ivSongCoverArt = itemView.findViewById(R.id.iv_song_coverart);
            constraintLayout=itemView.findViewById(R.id.cl_song_item);
        }
    }

    public void setSongItemClickListener(SongItemClickListener songItemClickListener) {
        this.songItemClickListener = songItemClickListener;
    }

    // Custom Interfaces and its method implementations
    public interface SongItemClickListener {
        void onSongItemClick(View v, SongModel song, int pos);
    }

}
