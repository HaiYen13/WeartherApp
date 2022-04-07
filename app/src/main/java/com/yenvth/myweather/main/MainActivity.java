package com.yenvth.myweather.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yenvth.myweather.R;
import com.yenvth.myweather.charts.ForecastFragment;
import com.yenvth.myweather.home.HomeFragment;

public class MainActivity extends AppCompatActivity  {
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    //Todo: thanh Search



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTablayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.vp_view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTablayout.setupWithViewPager(mViewPager);

    }

//    @Override
//    public void sentData(String nameOfCity) {
//        ForecastFragment forecastFragment = (ForecastFragment) getSupportFragmentManager().findFragmentById(R.id.content_forecastFragment);
//        forecastFragment.receiverDataFromFragment(nameOfCity);
//    }
}
