package com.blacpythoz.musik.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.blacpythoz.musik.models.SongModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "music-database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favourite";

    private static final String KEY_ID = "id";
    private static final String KEY_TRACK_NUMBER = "trackNumber";
    private static final String KEY_YEAR = "year";
    private static final String KEY_ALBUM_ID = "albumId";
    private static final String KEY_ARTIST_ID = "artistId";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_DATE_MODIFIED = "dateModified";
    private static final String KEY_DATE_ADDED = "dateAdded";
    private static final String KEY_BOOKMARK = "bookmark";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ARTIST_NAME = "artistName";
    private static final String KEY_COMPOSER = "composer";
    private static final String KEY_ALBUM_NAME = "albumName";
    private static final String KEY_ALBUM_ART = "albumArt";
    private static final String KEY_DATA = "data";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFavouriteTable = String.format("CREATE TABLE %s(" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "%s LONG, " +
                        "%s LONG, " +
                        "%s LONG, " +
                        "%s LONG, " +
                        "%s STRING, " +
                        "%s STRING, " +
                        "%s STRING, " +
                        "%s STRING, " +
                        "%s STRING, " +
                        "%s STRING)",
                TABLE_NAME,
                KEY_ID,
                KEY_TRACK_NUMBER,
                KEY_YEAR,
                KEY_ALBUM_ID,
                KEY_ARTIST_ID,
                KEY_DURATION,
                KEY_DATE_MODIFIED,
                KEY_DATE_ADDED,
                KEY_BOOKMARK,
                KEY_TITLE,
                KEY_ARTIST_NAME,
                KEY_COMPOSER,
                KEY_ALBUM_NAME,
                KEY_ALBUM_ART,
                KEY_DATA);
        db.execSQL(createFavouriteTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    public void addFavouriteSong(SongModel song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, song.getId());
        values.put(KEY_TRACK_NUMBER, song.getTrackNumber());
        values.put(KEY_YEAR, song.getYear());
        values.put(KEY_ALBUM_ID, song.getAlbumId());
        values.put(KEY_ARTIST_ID, song.getArtistId());
        values.put(KEY_DURATION, song.getDuration());
        values.put(KEY_DATE_MODIFIED, song.getDateModified());
        values.put(KEY_DATE_ADDED, song.getDateAdded());
        values.put(KEY_BOOKMARK, song.getBookmark());
        values.put(KEY_TITLE, song.getTitle());
        values.put(KEY_ARTIST_NAME, song.getArtistName());
        values.put(KEY_COMPOSER, song.getComposer());
        values.put(KEY_ALBUM_NAME, song.getAlbumName());
        values.put(KEY_ALBUM_ART, song.getAlbumArt());
        values.put(KEY_DATA, song.getData());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<SongModel> getAllFavouriteSongs() {
        List<SongModel> songList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            SongModel song = new SongModel(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getLong(5),
                    cursor.getLong(6),
                    cursor.getLong(7),
                    cursor.getLong(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getString(13),
                    cursor.getString(14));

            song.setFavourite(true);

            File file = new File(song.getData());
            if (!file.exists()) {
                deleteFavouriteSong(song.getId());
            } else {
                songList.add(song);
            }

            cursor.moveToNext();
        }
        cursor.close();
        return songList;
    }

    public void deleteFavouriteSong(int songId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(songId)});
        db.close();
    }

    public boolean checkAddedFavourite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_NAME + " where " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}