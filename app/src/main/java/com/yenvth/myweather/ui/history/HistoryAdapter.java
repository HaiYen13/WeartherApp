package com.yenvth.myweather.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yenvth.myweather.R;
import com.yenvth.myweather.models.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHolder>{
    private Context context;
    private ArrayList<History> dataList;

    public HistoryAdapter(Context context, ArrayList<History> dataList){
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
        History model = dataList.get(position);

        switch (model.getImg()){

            case "01d" :
            case "01n" : {
                holder.imgHis.setImageResource(R.drawable.icon_clear_sky);
                break;
            }
            case "02d" :
            case "02n" : {
                holder.imgHis.setImageResource(R.drawable.icon_few_clouds);
                break;
            }
            case "03d" :
            case "03n" : {
                holder.imgHis.setImageResource(R.drawable.icon_scattered_clouds);
                break;
            }
            case "04d" :
            case "04n" : {
                holder.imgHis.setImageResource(R.drawable.icon_broken_clouds);
                break;
            }
            case "09d" :
            case "09n" : {
                holder.imgHis.setImageResource(R.drawable.icon_shower_rain);
                break;
            }
            case "10d" :
            case "10n" : {
                holder.imgHis.setImageResource(R.drawable.icon_rain);
                break;
            }
            case "11d" :
            case "11n" : {
                holder.imgHis.setImageResource(R.drawable.icon_thunderstorm);
                break;
            }
            case "13d" :
            case "13n" : {
                holder.imgHis.setImageResource(R.drawable.icon_snow);
                break;
            }
            case "50d" :
            case "50n" : {
                holder.imgHis.setImageResource(R.drawable.icon_mist1);
                break;
            }
            default:
                holder.imgHis.setImageResource( R.drawable.icon_broken_clouds);
        }

        holder.tvLocationHistory.setText(model.getName_city());
        holder.tvDesHis.setText(model.getDescription());
        holder.tvTempHis.setText(model.getTemp()+"");
        holder.tvPresHis.setText(model.getPressure()+"");
        holder.tvHumiHis.setText(model.getHumidity()+"");
        holder.tvDateHis.setText(model.getDate());
        holder.tvTimeHis.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView imgHis;
        private TextView tvLocationHistory;
        private TextView tvDesHis;
        private TextView tvTempHis;
        private TextView tvPresHis;
        private TextView tvHumiHis;
        private TextView tvDateHis;
        private TextView tvTimeHis;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgHis = itemView.findViewById(R.id.imgHis);
            tvLocationHistory = itemView.findViewById(R.id.tvLocationHistory);
            tvDesHis = itemView.findViewById(R.id.tvDesHis);
            tvTempHis = itemView.findViewById(R.id.tvTempHis);
            tvPresHis = itemView.findViewById(R.id.tvPresHis);
            tvHumiHis = itemView.findViewById(R.id.tvHumiHis);
            tvDateHis = itemView.findViewById(R.id.tvDateHis);
            tvTimeHis = itemView.findViewById(R.id.tvTimeHis);
        }
    }
}