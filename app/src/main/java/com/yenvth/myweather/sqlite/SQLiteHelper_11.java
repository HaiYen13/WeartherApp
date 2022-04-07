package com.yenvth.myweather.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.yenvth.myweather.models.History;

public class SQLiteHelper_11 extends SQLiteOpenHelper {
    private static final String TAG = "SQLHelper";
    static final String DB_NAME = "Weather.db";
    static final String DB_NAME_TABLE = "History";
    static final int DB_VERSION = 1;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;


    public SQLiteHelper_11(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: tạo bảng
        String queryHisCreaTable = "CREATE TABLE History ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "                city VARCHAR(20) NOT NULL ," +
                "                date VARCHAR(20) NOT NULL ," +
                "                time VARCHAR(20) NOT NULL ," +
                "                img VARCHAR(25) NOT NULL ," +
                "                description VARCHAR(30) NOT NULL ," +
                "                temperature FLOAT NOT NULL ," +
                "                pressure INTERGER NOT NULL ," +
                "                humidity INTERGER NOT NULL)";
        db.execSQL(queryHisCreaTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == newVersion){
            db.execSQL("drop table if exists " + DB_NAME_TABLE);
            onCreate(db);
        }

    }
    // TODO: Thêm vào database
    public void insertHistory(History history){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("city", history.getName_city());
        contentValues.put("date", history.getDate());
        contentValues.put("time", history.getTime());
        contentValues.put("img", history.getImg());
        contentValues.put("description", history.getDescription());
        contentValues.put("temperature", history.getTemp());
        contentValues.put("pressure", history.getPressure());
        contentValues.put("humidity", history.getHumidity());
        // TODO: Add vao bang
        sqLiteDatabase.insert(DB_NAME_TABLE, null, contentValues);
        close();
    }
    //TODO: xoa toan bo ban ghi
    public boolean deleteAll(){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_NAME_TABLE, null, null);
        close();
        return true;

    }

    @Override
    public synchronized void close() {
        if(sqLiteDatabase != null) sqLiteDatabase.close();
        if(contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }
    //TODO: tra ve 6 lich su gan nhat

}
