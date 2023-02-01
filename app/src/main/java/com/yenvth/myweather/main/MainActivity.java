package com.yenvth.myweather.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yenvth.myweather.R;
import com.yenvth.myweather.ui.charts.ForecastFragment;
import com.yenvth.myweather.ui.history.HistoryFragment;
import com.yenvth.myweather.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    //Todo: thanh Search
    private List<Fragment> list;

    private HomeFragment homeFragment;
    private ForecastFragment forecastFragment;
    private HistoryFragment historyFragment;
    public static long cityId = 1580578;

    public static String city = "Saigon";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTablayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.vp_view_pager);

        list = new ArrayList<>();
        homeFragment = new HomeFragment();
        forecastFragment = new ForecastFragment();
        historyFragment = new HistoryFragment();
        list.add(homeFragment);
        list.add(forecastFragment);
        list.add(historyFragment);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTablayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 1:
                        forecastFragment.getThreeHour(cityId,city);
                        break;
                    case 2:
                        historyFragment.getData();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
