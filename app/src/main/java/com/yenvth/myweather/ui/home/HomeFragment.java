package com.yenvth.myweather.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yenvth.myweather.R;
import com.yenvth.myweather.api.ApiService;
import com.yenvth.myweather.main.MainActivity;
import com.yenvth.myweather.models.citylist.CityListModel;
import com.yenvth.myweather.models.History;
import com.yenvth.myweather.models.mfivenextdaysmodels.NextFiveDaysWeatherModel;
import com.yenvth.myweather.models.currentweather.CityWeatherModel;
import com.yenvth.myweather.sqlite.SQLiteHelper;
import com.yenvth.myweather.utils.CityList;
import com.yenvth.myweather.utils.DateUtils;
import com.yenvth.myweather.utils.WeatherUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    SQLiteHelper sqLiteHelper;

    //Todo: Thời tiết hiện tại
    private TextView tvTemperatureCurrent;
    private TextView tvLocationCurrent;
    private TextView tvDes;
    private TextView tvPressureCurrent;
    private TextView tvHumidityCurrent;
    private TextView tvWindCurrent;
    private TextView tvCurrentDate;
    private String currentDate;
    private long cityId;

    //Todo: nhiệt độ 5 ngày tiếp theo
    private TextView tvTempNext1Days;
    private TextView tvTempNext2Days;
    private TextView tvTempNext3Days;
    private TextView tvTempNext4Days;
    private TextView tvTempNext5Days;
    //Todo: ngày tháng 5 ngày tiếp theo
    private String date1;
    private String date2;
    private String date3;
    private String date4;
    private String date5;
    private TextView tvNext1DaysDate;
    private TextView tvNext2DaysDate;
    private TextView tvNext3DaysDate;
    private TextView tvNext4DaysDate;
    private TextView tvNext5DaysDate;
    private int daysOfWeek1;
    private int daysOfWeek2;
    private int daysOfWeek3;
    private int daysOfWeek4;
    private int daysOfWeek5;
    private TextView tvDaysOfWeek1;
    private TextView tvDaysOfWeek2;
    private TextView tvDaysOfWeek3;
    private TextView tvDaysOfWeek4;
    private TextView tvDaysOfWeek5;
    private ImageView imCurrentWeather;
    private ImageView imNext1DaysWeather;
    private ImageView imNext2DaysWeather;
    private ImageView imNext3DaysWeather;
    private ImageView imNext4DaysWeather;
    private ImageView imNext5DaysWeather;

    private AutoCompleteTextView autoCompleteTextView;
    private ImageView imSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sqLiteHelper = new SQLiteHelper(getContext());
        initViews(view);        //Find view by id
        initAction();          // Xử lý logic, ...\

        Gson gson = new Gson(); //khởi  tạo đối tượng
        String cityList = CityList.loadJsonFromAsset(getContext());
        List<CityListModel> cityListModel = gson.fromJson(cityList, new TypeToken<List<CityListModel>>() {
        }.getType());
        ArrayList<String> cities = new ArrayList<>();
        for (int i = 0; i < cityListModel.size(); i++) {
            cities.add(cityListModel.get(i).getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cities);
        autoCompleteTextView.setAdapter(arrayAdapter);

        getCurrentWeatherData("Saigon");

        return view;
    }

    private void initViews(View view){
        tvTemperatureCurrent = view.findViewById(R.id.tvTemperatureCurrent);
        tvLocationCurrent = view.findViewById(R.id.tvLocationCurrent);
        tvDes = view.findViewById(R.id.tvDes);
        tvPressureCurrent = view.findViewById(R.id.tvPressureCurrent);
        tvHumidityCurrent = view.findViewById(R.id.tvHumidityCurrent);
        tvWindCurrent = view.findViewById(R.id.tvWindCurrent);
        tvCurrentDate = view.findViewById(R.id.tvCurrentDate);
        //Todo: nhiệt độ 5 ngày tiếp theo
        tvTempNext1Days = view.findViewById(R.id.tvTempNext1Days);
        tvTempNext2Days = view.findViewById(R.id.tvTempNext2Days);
        tvTempNext3Days = view.findViewById(R.id.tvTempNext3Days);
        tvTempNext4Days = view.findViewById(R.id.tvTempNext4Days);
        tvTempNext5Days = view.findViewById(R.id.tvTempNext5Days);
        //Todo: ngày tháng 5 ngày tiếp theo
        tvNext1DaysDate = view.findViewById(R.id.tvNext1DaysDate);
        tvNext2DaysDate = view.findViewById(R.id.tvNext2DaysDate);
        tvNext3DaysDate = view.findViewById(R.id.tvNext3DaysDate);
        tvNext4DaysDate = view.findViewById(R.id.tvNext4DaysDate);
        tvNext5DaysDate = view.findViewById(R.id.tvNext5DaysDate);
        //Todo: lấy thứ trong tuần
        tvDaysOfWeek1 = view.findViewById(R.id.tvDaysOfWeek1);
        tvDaysOfWeek2 = view.findViewById(R.id.tvDaysOfWeek2);
        tvDaysOfWeek3 = view.findViewById(R.id.tvDaysOfWeek3);
        tvDaysOfWeek4 = view.findViewById(R.id.tvDaysOfWeek4);
        tvDaysOfWeek5 = view.findViewById(R.id.tvDaysOfWeek5);
        //Todo: lay icon
        imCurrentWeather = view.findViewById(R.id.imCurrentWeather);

        imNext1DaysWeather = view.findViewById(R.id.imNext1DaysWeather);
        imNext2DaysWeather = view.findViewById(R.id.imNext2DaysWeather);
        imNext3DaysWeather = view.findViewById(R.id.imNext3DaysWeather);
        imNext4DaysWeather = view.findViewById(R.id.imNext4DaysWeather);
        imNext5DaysWeather = view.findViewById(R.id.imNext5DaysWeather);
        //Todo: Anh xa thanh search
        autoCompleteTextView = view.findViewById(R.id.attvSearchCountry);
        imSearch = view.findViewById(R.id.imSearch);
    }

    private void initAction(){
        imSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = autoCompleteTextView.getText().toString();
                getCurrentWeatherData(cityName);
                autoCompleteTextView.setText("");       //Clear tìm kiếm
            }
        });
    }
    public void getCurrentWeatherData(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String ulrCurrent = "https://api.openweathermap.org/data/2.5/find?q=" + data + "&units=metric&appid=a34d6ef850a2d943daa1ca646328a3f0&lang=en";
        String urlNextFiveDays = "https://api.openweathermap.org/data/2.5/forecast/daily?appid=211ff006de9aba9ddd122331f87cdf8b&q=" + data + "&cnt=6&units=metric";
        StringRequest currentWeatherRequest = new StringRequest(Request.Method.GET, ulrCurrent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("CurrentWeather", response);
                        //Todo: Mapping sang model
                        Gson gson = new Gson(); //khởi  tạo đối tượng
                        CityWeatherModel cityWeatherModel = gson.fromJson(response, CityWeatherModel.class);// Convert từ string sang object
//                        String json = gson.toJson(cityWeatherModel);        // Convert từ object sang chuỗi json (string)
                        NextFiveDaysWeatherModel nextFiveDaysWeatherModel = gson.fromJson(response, NextFiveDaysWeatherModel.class);

                        tvTemperatureCurrent.setText(cityWeatherModel.getList().get(0).getMain().getTemp() + " °C");
                        Log.d("lay duoc ket qua temp", cityWeatherModel.getList().get(0).getMain().getTemp() + " °C");
                        tvLocationCurrent.setText(cityWeatherModel.getList().get(0).getName() + "");
                        cityId = cityWeatherModel.getList().get(0).getId();
                        tvDes.setText(cityWeatherModel.getList().get(0).getWeather().get(0).getDescription() + "");
                        tvPressureCurrent.setText(cityWeatherModel.getList().get(0).getMain().getPressure() + " hPa");
                        tvHumidityCurrent.setText(cityWeatherModel.getList().get(0).getMain().getHumidity() + " %");
                        tvWindCurrent.setText(cityWeatherModel.getList().get(0).getWind().getSpeed() + " m/s");
                        currentDate = DateUtils.getDateFromTimestamp(nextFiveDaysWeatherModel.getList().get(0).getDt() * 1000, DateUtils.FORMAT_6);
                        tvCurrentDate.setText(currentDate);
                        imCurrentWeather.setImageResource(WeatherUtils.getIcon(cityWeatherModel.getList().get(0).getWeather().get(0).getIcon()));


                        History history = new History(
                                cityWeatherModel.getList().get(0).getName(),
                                DateUtils.getDateFromTimestamp(nextFiveDaysWeatherModel.getList().get(0).getDt() * 1000, DateUtils.FORMAT_6),
                                DateUtils.getDateFromTimestamp(nextFiveDaysWeatherModel.getList().get(0).getDt() * 1000, DateUtils.FORMAT_3),
                                cityWeatherModel.getList().get(0).getWeather().get(0).getIcon(),
                                cityWeatherModel.getList().get(0).getWeather().get(0).getDescription(),
                                cityWeatherModel.getList().get(0).getMain().getTemp(),
                                cityWeatherModel.getList().get(0).getMain().getHumidity(),
                                cityWeatherModel.getList().get(0).getMain().getHumidity()
                        );
                        MainActivity.cityId = cityWeatherModel.getList().get(0).getId();
                        MainActivity.city = cityWeatherModel.getList().get(0).getName();
                        sqLiteHelper.insertHistory(history);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Todo: 5 ngay tiep theo
        StringRequest nextFiveDaysWeatherRequest = new StringRequest(Request.Method.GET, urlNextFiveDays,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response2) {
                        Log.d("5ngaytiep", response2);
                        Gson gson = new Gson(); // Khởi tạo đối tượng mới
                        NextFiveDaysWeatherModel nextFiveDaysWeatherModel = gson.fromJson(response2, NextFiveDaysWeatherModel.class);
                        tvTempNext1Days.setText(nextFiveDaysWeatherModel.getList().get(0).getTemp().getDay() + " °C");
                        tvTempNext2Days.setText(nextFiveDaysWeatherModel.getList().get(1).getTemp().getDay() + " °C");
                        tvTempNext3Days.setText(nextFiveDaysWeatherModel.getList().get(2).getTemp().getDay() + " °C");
                        tvTempNext4Days.setText(nextFiveDaysWeatherModel.getList().get(3).getTemp().getDay() + " °C");
                        tvTempNext5Days.setText(nextFiveDaysWeatherModel.getList().get(4).getTemp().getDay() + " °C");
                        //Todo: lấy ngày tháng 5 ngày tiếp theo
                        date1 = DateUtils.getDateFromTimestamp(nextFiveDaysWeatherModel.getList().get(1).getDt() * 1000, DateUtils.FORMAT_6);
                        date2 = DateUtils.getDateFromTimestamp(nextFiveDaysWeatherModel.getList().get(2).getDt() * 1000, DateUtils.FORMAT_6);
                        date3 = DateUtils.getDateFromTimestamp(nextFiveDaysWeatherModel.getList().get(3).getDt() * 1000, DateUtils.FORMAT_6);
                        date4 = DateUtils.getDateFromTimestamp(nextFiveDaysWeatherModel.getList().get(4).getDt() * 1000, DateUtils.FORMAT_6);
                        date5 = DateUtils.getDateFromTimestamp(nextFiveDaysWeatherModel.getList().get(5).getDt() * 1000, DateUtils.FORMAT_6);
                        tvNext1DaysDate.setText(date1);
                        tvNext2DaysDate.setText(date2);
                        tvNext3DaysDate.setText(date3);
                        tvNext4DaysDate.setText(date4);
                        tvNext5DaysDate.setText(date5);
                        //Todo: Lay thu trong tuan
                        daysOfWeek1 = DateUtils.getDayOfWeek(nextFiveDaysWeatherModel.getList().get(1).getDt() * 1000);
                        daysOfWeek2 = DateUtils.getDayOfWeek(nextFiveDaysWeatherModel.getList().get(2).getDt() * 1000);
                        daysOfWeek3 = DateUtils.getDayOfWeek(nextFiveDaysWeatherModel.getList().get(3).getDt() * 1000);
                        daysOfWeek4 = DateUtils.getDayOfWeek(nextFiveDaysWeatherModel.getList().get(4).getDt() * 1000);
                        daysOfWeek5 = DateUtils.getDayOfWeek(nextFiveDaysWeatherModel.getList().get(5).getDt() * 1000);
                        tvDaysOfWeek1.setText(DateUtils.getDayOfWeek(daysOfWeek1) + "");
                        tvDaysOfWeek2.setText(DateUtils.getDayOfWeek(daysOfWeek2) + "");
                        tvDaysOfWeek3.setText(DateUtils.getDayOfWeek(daysOfWeek3) + "");
                        tvDaysOfWeek4.setText(DateUtils.getDayOfWeek(daysOfWeek4) + "");
                        tvDaysOfWeek5.setText(DateUtils.getDayOfWeek(daysOfWeek5) + "");
                        imNext1DaysWeather.setImageResource(WeatherUtils.getIcon(nextFiveDaysWeatherModel.getList().get(1).getWeather().get(0).getIcon()));
                        imNext2DaysWeather.setImageResource(WeatherUtils.getIcon(nextFiveDaysWeatherModel.getList().get(2).getWeather().get(0).getIcon()));
                        imNext3DaysWeather.setImageResource(WeatherUtils.getIcon(nextFiveDaysWeatherModel.getList().get(3).getWeather().get(0).getIcon()));
                        imNext4DaysWeather.setImageResource(WeatherUtils.getIcon(nextFiveDaysWeatherModel.getList().get(4).getWeather().get(0).getIcon()));
                        imNext5DaysWeather.setImageResource(WeatherUtils.getIcon(nextFiveDaysWeatherModel.getList().get(5).getWeather().get(0).getIcon()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(currentWeatherRequest);
        requestQueue.add(nextFiveDaysWeatherRequest);
    }
}
