package com.hsw.indictordemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.hsw.indictordemo.indicator.ViewPagerIndicator;

public class ViewPageIndicatorDemoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_indicator);
        viewPager = findViewById(R.id.view_pager);
        indicator = findViewById(R.id.indicator);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return ItemListDialogFragment.newInstance((position + 1) * 2);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        indicator.setWithViewPager(viewPager);
    }
}
