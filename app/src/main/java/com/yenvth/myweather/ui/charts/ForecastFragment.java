package com.yenvth.myweather.ui.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.yenvth.myweather.R;
import com.yenvth.myweather.models.threehoursweather.CityWeatherModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ForecastFragment extends Fragment implements OnChartValueSelectedListener {

    private static String TAG = "ForecastFragment";
    private static int COUNT = 5;
    private CombinedChart mChart;
    private TextView tvCityName;
    private int tempOfCities;
    private ArrayList<Float> temperatures = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        tvCityName = view.findViewById(R.id.tvCityName);
        mChart = view.findViewById(R.id.combinedChart);
        //TODO: set up cho combinedChart
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);

        return view;
    }

    //lay data
    public void getThreeHour(long cityId, String cityName) {
        dates.clear();// clear tránh trường hợp add vào list rồi lại add tiếp
        times.clear();
        temperatures.clear();

        //TODO: set up ten thanh pho va get du lieu
        tvCityName.setText("City: " + cityName);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String threeHoursUrl = "https://api.openweathermap.org/data/2.5/forecast?id=" + cityId + "&appid=211ff006de9aba9ddd122331f87cdf8b&cnt=6&units=metric";
        StringRequest threehoursRequest = new StringRequest(Request.Method.GET, threeHoursUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("3 tieng trong ngay", response);
                Gson gson = new Gson(); // Khởi tạo đối tượng mới
                CityWeatherModel cityWeatherModel = gson.fromJson(response, CityWeatherModel.class);

                tvCityName.setText(cityWeatherModel.getCity().getName() + "");


                Log.d("ten thanh pho bieu do", cityWeatherModel.getCity().getName() + "");
                tempOfCities = cityWeatherModel.getList().size();
                for (int i = 0; i < tempOfCities; i++) {
                    if (cityWeatherModel.getList().get(i).getMain() != null) {
                        temperatures.add((float) cityWeatherModel.getList().get(i).getMain().getTemp());
                    }
                }
                for (int i = 0; i < tempOfCities; i++) {
                    if (cityWeatherModel.getList().get(i).getDt_txt() == null) {
                        continue;
                    }
                    dates.add(cityWeatherModel.getList().get(i).getDt_txt());
                    Calendar c = getTime(dates.get(i));
                    dates.set(i, c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + 1);

                    times.add(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));

                }

                CombinedData data = new CombinedData();
                data.setData(generateLineChart());
                mChart.setData(data);
                mChart.invalidate();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        requestQueue.add(threehoursRequest);


    }

    public static Calendar getTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        Calendar calendar = null;
        try {
            Date date = sdf.parse(time);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    //TODO: tao bieu do duong Line Chart
    private LineData generateLineChart() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < COUNT; i++) {
            entries.add(new Entry(1f * i, temperatures.get(i)));
        }
        LineDataSet set = new LineDataSet(entries, "temperature");
        set.setColor(Color.RED);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.RED);
        set.setCircleRadius(Color.RED);
        set.setFillColor(Color.RED);
        set.setCircleRadius(5f);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.RED);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set);
        //TODO: tao truc tung ben phai
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);
//        rightAxis.setStartAtZero(false);

        //TODO: tao truc tung ben trai
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        //TODO: tao nhan cho truc hoanh
        final List<String> xLabel = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            if (times.get(i).equals("0:0") && times.get(i + 1).equals("15:00")) {
                xLabel.add("12:00");
            } else
                xLabel.add(times.get(i));
        }
        //TODO: tao truc hoanh
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value % xLabel.size());
            }
        });
//        xAxis.setAxisMaximum(data.getXMax()+ 0.25f);
        return d;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
