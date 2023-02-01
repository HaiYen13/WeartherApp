package com.yenvth.myweather.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yenvth.myweather.R;
import com.yenvth.myweather.models.History;
import com.yenvth.myweather.sqlite.SQLiteHelper;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    //    private RecyclerView rvHis;
//    private HistoryAdapter adapter;
//    private ArrayList<String> dataList;
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_history, container, false);
//        rvHis = view.findViewById(R.id.rvHis);
//        //Todo: Khởi tạo dữ liệu
//        dataList = new ArrayList<>();
//        dataList.add("VietNam");
//        dataList.add("Singapore");
//        dataList.add("Korea");
//        dataList.add("Korea");
//        dataList.add("Korea");
//        //Todo: Khởi tạo Adapter
//        adapter = new HistoryAdapter(getContext(), dataList);
//
//        //Todo: Gán Adapter cho recyclerView
//        rvHis.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
//        rvHis.setAdapter(adapter);
//        return view;
//    }
    private RecyclerView mRcvData;
    private HistoryAdapter mAdapter;
    private ArrayList<History> mListHistory;
    private SQLiteHelper sqLiteHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        mRcvData = v.findViewById(R.id.rvHis);
        return v;


    }
    //TODO: lay data History tu database
    public void getData(){
        sqLiteHelper = new SQLiteHelper(getActivity());
        mListHistory = sqLiteHelper.getArrayHistory();

        mAdapter = new HistoryAdapter(getContext(), mListHistory);
        mRcvData.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRcvData.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}