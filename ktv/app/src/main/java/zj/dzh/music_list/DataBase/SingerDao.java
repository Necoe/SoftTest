package zj.dzh.music_list.DataBase;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import zj.dzh.music_list.bean.Singer;

public class SingerDao {
    private Context context;        // 上下文
    private DataBaseHelper dbHelper; // 数据库管理对象
    private SQLiteDatabase db;      // 可对数据库进行读写的操作对象

    public SingerDao(Context context) {
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

    // 添加歌手信息
    public void addSinger(Singer singer) {
        ContentValues values = new ContentValues();
        values.put("name", singer.getName());
        values.put("sex", singer.getSex());
        values.put("work", singer.getWork());
        values.put("success", singer.getSuccess());
        values.put("imgId", String.valueOf(singer.getImgId()));
        db.insert("singer", null, values);
    }

    // 删除歌手信息
    public void deleteSinger(Singer singer) {
        db.delete("singer", "name = ?", new String[]{singer.getName()});
    }

    // 修改歌手信息
    public void updateSinger(Singer singer) {
        ContentValues values = new ContentValues();
        values.put("sex", singer.getSex());
        values.put("work", singer.getWork());
        values.put("success", singer.getSuccess());
        values.put("imgId", String.valueOf(singer.getImgId()));
        db.update("singer", values, "name = ?", new String[]{singer.getName()});
    }

    // 查询歌手信息
    public Singer findSinger(String name) {
        Singer singer = null;
        // 查询singer表中where指定列元素的记录
        Cursor cursor = db.query("singer", null, "name = ?", new String[]{name}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            singer = new Singer(
                    ((Cursor) cursor).getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("sex")),
                    cursor.getString(cursor.getColumnIndex("work")),
                    cursor.getString(cursor.getColumnIndex("success")),
                    cursor.getInt(cursor.getColumnIndex("imgId"))
            );
        }
        cursor.close();
        return singer;
    }

    // 根据歌手姓名来查找歌手信息
    public Singer getSingerByName(String name) {
        return findSinger(name);
    }
}
