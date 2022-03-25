package com.yenvth.myweather.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.yenvth.myweather.models.History;

import java.util.ArrayList;
import java.util.Stack;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLHelper";
    static final String DB_NAME = "Weather.db";     //Tên database
    static final int DB_VERSION = 1;               // Phiên bản
    static final String DB_NAME_TABLE = "History";  //Tên bảng

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Todo: Câu lệnh truy vấn tạo Bảng History
        String queryHisCreaTable = "CREATE TABLE History ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "                city VARCHAR(20) NOT NULL ," +
                "                date VARCHAR(20) NOT NULL ," +
                "                time VARCHAR(20) NOT NULL ," +
                "                img VARCHAR(25) NOT NULL ," +
                "                description VARCHAR(30) NOT NULL ," +
                "                temperature FLOAT NOT NULL ," +
                "                pressure INTERGER NOT NULL ," +
                "                humidity INTERGER NOT NULL)";
        //Todo: Thi hành câu lệnh trên
        db.execSQL(queryHisCreaTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == newVersion) {
            db.execSQL("drop table if exists " + DB_NAME_TABLE);
            onCreate(db);
        }
    }

    // TODO: Thêm bảng ghi vào History
    public void insertHistory(History history) {
        //TODO: Database o dang chinh sua
        sqLiteDatabase = getWritableDatabase();

        //TODO: tao bien noi dung can them
        contentValues = new ContentValues();
        contentValues.put("city", history.getName_city());
        contentValues.put("date", history.getDate());
        contentValues.put("time", history.getTime());
        contentValues.put("img", history.getImg());
        contentValues.put("description", history.getDescription());
        contentValues.put("temperature", history.getTemp());
        contentValues.put("pressure", history.getPressure());
        contentValues.put("humidity", history.getHumidity());
        // ToDO: Theem vafo banrg
        sqLiteDatabase.insert(DB_NAME_TABLE, null, contentValues);
        close();
    }

    // TODO: xoa toan bo ban ghi
    public boolean deleteAll() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_NAME_TABLE, null, null);
        closeDB();
        return true;
    }

    // TODO: Dong Database
    private void closeDB() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }

    //TODO: tra ve mang history gom 6 phan tu gan nhat
    public ArrayList<History> getArrayHistory() {
        Stack<History> stack = new Stack<>();
        ArrayList<History> histories = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(true, DB_NAME_TABLE, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("city"));// view du lieu ra 
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String img = cursor.getString(cursor.getColumnIndex("img"));
            String des = cursor.getString(cursor.getColumnIndex("description"));
            float temp = cursor.getFloat(cursor.getColumnIndex("temperature"));
            int pressure = cursor.getInt(cursor.getColumnIndex("pressure"));
            int humi = cursor.getInt(cursor.getColumnIndex("humidity"));

            History history = new History(name, date, time, img, des, temp, pressure, humi);
            stack.push(history);
        }
        if (stack.isEmpty()) return histories;
        History his1 = stack.peek();
        stack.pop();
        int i = 1;
        while (!stack.isEmpty()) {
            if (i == 6)
                break;

            History his2 = stack.peek();
            //TODO: day la truong hop nguoi dung nhan nhieu button search cung 1 dia diem, cung mot thoi gian
            if (!his1.getName_city().equals(his2.getName_city())
                    || (his1.getImg().equals(his2.getDescription()))
                    || (his1.getDescription().equals(his2.getDescription()))
                    || (his1.getDate().equals(his2.getDate()))
                    || (his1.getTime().equals(his2.getTime()))
                    || (his1.getTemp() != his2.getTemp())
                    || (his1.getPressure() != his2.getPressure())
                    || (his1.getHumidity() != his2.getHumidity())) {
                histories.add(his1);
                his1 = his2;
                i++;
                stack.pop();
            } else
                stack.pop();
        }
        return histories;
    }
}
