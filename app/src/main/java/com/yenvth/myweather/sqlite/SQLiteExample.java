package com.yenvth.myweather.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteExample extends SQLiteOpenHelper {

    public SQLiteExample(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    // TODO: Truy vẫn không kết quả: create, insert, update, delete

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    // TODO: Truy vấn trả kết quả: select trả về dạng con trỏ
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase(); // ToDo: getWrite cũng được vì Write cũng phải đọc xong mới ghi
        return database.rawQuery(sql, null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
