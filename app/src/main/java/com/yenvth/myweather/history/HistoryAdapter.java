package com.yenvth.myweather.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yenvth.myweather.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHolder>{
    private Context context;
    private ArrayList<String> dataList;
    public HistoryAdapter(Context context, ArrayList<String> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public HistoryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Todo: Ham nay tra ve 1 layout cho tung ViewHolder
    View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyHolder holder, int position) {
        String str = dataList.get(position);
        holder.tvLocationHistory.setText(str);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvLocationHistory;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvLocationHistory = itemView.findViewById(R.id.tvLocationHistory);
        }
    }
}
