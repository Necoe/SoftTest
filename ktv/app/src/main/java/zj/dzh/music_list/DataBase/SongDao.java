package zj.dzh.music_list.DataBase;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import zj.dzh.music_list.bean.Song;

public class SongDao {
    private Context context;        // 上下文
    private DataBaseHelper dbHelper; // 数据库管理对象
    private SQLiteDatabase db;      // 可对数据库进行读写的操作对象

    public SongDao(Context context) {
        this.context = context;
    }

    // 创建并打开数据库（如果数据库已存在直接打开）
    public void open() throws SQLiteException {
        dbHelper = new DataBaseHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException exception) {
            db = dbHelper.getReadableDatabase();
        }
    }

    // 关闭数据库
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    // 添加歌曲信息
    public void addSong(Song song) {
        ContentValues values = new ContentValues();
        values.put("songId", song.getSongId());
        values.put("songName", song.getSongName());
        values.put("songType", song.getSongType());
        values.put("singer_Id", song.getSinger_Id());
        values.put("imgId", song.getImgId());
        db.insert("song", null, values);
    }

    // 删除歌曲信息
    public void deleteSong(Song song) {
        db.delete("song", "songId = ?", new String[]{song.getSongId()});
    }

    // 修改歌曲信息
    public void updateSong(Song song) {
        ContentValues values = new ContentValues();
        values.put("songName", song.getSongName());
        values.put("songType", song.getSongType());
        values.put("singer_Id", song.getSinger_Id());
        values.put("imgId", song.getImgId());
        db.update("song", values, "songId = ?", new String[]{song.getSongId()});
    }

    // 查询歌曲信息
    @SuppressLint("Range")
    public Song findSong(String songId) {
        Song song = null;
        // 查询song表中where指定列元素的记录
        Cursor cursor = db.query("song", null, "songId = ?", new String[]{songId}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            song = new Song();
            song.setSongId(cursor.getString(cursor.getColumnIndex("songId")));
            song.setSongName(cursor.getString(cursor.getColumnIndex("songName")));
            song.setSongType(cursor.getString(cursor.getColumnIndex("songType")));
            song.setSinger_Id(cursor.getString(cursor.getColumnIndex("singer_Id")));
            song.setImgId(cursor.getString(cursor.getColumnIndex("imgId")));
        }
        cursor.close();
        return song;
    }
}