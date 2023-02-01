package com.yenvth.myweather.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    List<Fragment> list;
    public ViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
//        switch (position){
//            case 0:
//                return new HomeFragment();
//            case 1:
//                return new ForecastFragment();
//            case 2:
//                return new HistoryFragment();
//            default:
//                return new HomeFragment();
//        }
}

    @Override
    public int getCount() {
        return list.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position){
            case 0:
                title = "Home";
                break;
            case 1:
                title = "Forecast";
                break;
            case 2:
                title = "History";
                break;
        }
        return title;
    }
}
