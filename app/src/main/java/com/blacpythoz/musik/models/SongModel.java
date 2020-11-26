package com.blacpythoz.musik.models;

/**
 * Created by deadsec on 9/2/17.
 */

public class SongModel {

    private int id;
    private int trackNumber;
    private int year;
    private int albumId;
    private int artistId;
    private long duration;
    private long dateModified;
    private long dateAdded;
    private long bookmark;
    private String title;
    private String artistName;
    private String composer;
    private String albumName;
    private String albumArt;
    private String data;
    private boolean isFavourite;


    public static SongModel EMPTY() {
        return new SongModel(0, 0, 0, 0, 0, 0, 0, 0, 0, "", "", "", "", "", "");
    }

    public SongModel(int id, int trackNumber, int year, int albumId, int artistId, long duration, long dateModified, long dateAdded, long bookmark, String title, String artistName, String composer, String albumName, String albumArt, String data) {
        this.id = id;
        this.trackNumber = trackNumber;
        this.year = year;
        this.albumId = albumId;
        this.artistId = artistId;
        this.duration = duration;
        this.dateModified = dateModified;
        this.dateAdded = dateAdded;
        this.bookmark = bookmark;
        this.title = title;
        this.artistName = artistName;
        this.composer = composer;
        this.albumName = albumName;
        this.albumArt = albumArt;
        this.data = data;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getComposer() {
        return composer;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public String getData() {
        return data;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public int getYear() {
        return year;
    }

    public long getDuration() {
        return duration;
    }

    public long getDateModified() {
        return dateModified;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getArtistId() {
        return artistId;
    }

    public long getBookmark() {
        return bookmark;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}