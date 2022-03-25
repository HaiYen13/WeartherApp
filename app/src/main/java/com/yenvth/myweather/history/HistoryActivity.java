package com.yenvth.myweather.history;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yenvth.myweather.R;
import com.yenvth.myweather.sqlite.SQLiteExample;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
//    private RecyclerView rvHis;
//    private HistoryAdapter adapter;
//    private ArrayList<String> dataList;
//    SQLiteExample sqLiteExample;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_history);
//        rvHis = findViewById(R.id.rvHis);
//        //Todo: Khởi tạo dữ liệu
//        dataList = new ArrayList<>();
//        dataList.add("VietNam");
//        dataList.add("Singapore");
//        dataList.add("Korea");
//        dataList.add("Korea");
//        dataList.add("Korea");
//        //Todo: Khởi tạo Adapter
//        adapter = new HistoryAdapter(getApplication(), dataList);
//
//        //Todo: Gán Adapter cho recyclerView
//        rvHis.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        rvHis.setAdapter(adapter);
//        // TODO: tạo database
//        sqLiteExample = new SQLiteExample(this, "history", null, 1);
//        //TODO: tạo bảng history
//        sqLiteExample.QueryData("Create table if not exits History(id INTEGER PRIMERY KEY AUTOINCREMENT," +
//                " city VARCHAR(20) NOT NULL," +
//                " date VARCHAR(20) NOT NULL," +
//                " img VARCHAR(25) NOT NULL," +
//                " descrition VARCHAR(30) NOT NULL," +
//                " temperature FLOAT NOT NULL," +
//                " pressure FLOAT NOT NULL," +
//                " humidity FLOAT NOT NULL)");
//
//        //Todo: Insert data
////        sqLiteExample.QueryData("INSERT INTO History VALUES(1,'HaNoi', '16/03/2022','image', 'rain', '30', '100', '00.5')");
//        //TODO: Select data
//        Cursor weatherDataCursor = sqLiteExample.getData("SELECT *  FROM History");
//        while (weatherDataCursor.moveToNext()){
//            String city = weatherDataCursor.getString(1);
//            Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
//        }
//    }

}
