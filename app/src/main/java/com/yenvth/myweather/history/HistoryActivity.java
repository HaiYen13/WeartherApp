package com.yenvth.myweather.history;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yenvth.myweather.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rvHis;
    private HistoryAdapter adapter;
    private ArrayList<String> dataList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_history);
        rvHis = findViewById(R.id.rvHis);
        //Todo: Khởi tạo dữ liệu
        dataList = new ArrayList<>();
        dataList.add("VietNam");
        dataList.add("Singapore");
        dataList.add("Korea");
        dataList.add("Korea");
        dataList.add("Korea");
        //Todo: Khởi tạo Adapter
        adapter = new HistoryAdapter(getApplication(), dataList);

        //Todo: Gán Adapter cho recyclerView
        rvHis.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvHis.setAdapter(adapter);
    }

}
