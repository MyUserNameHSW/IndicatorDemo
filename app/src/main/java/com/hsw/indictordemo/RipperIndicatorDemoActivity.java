package com.hsw.indictordemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.hsw.indictordemo.dummy.DummyContent;
import com.hsw.indictordemo.tab.TabLayout;

public class RipperIndicatorDemoActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripper_indicator_demo);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return  ItemFragment.newInstance(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "item" + position + (position % 3 == 0 ? "benas" : "");
            }

            @Override
            public int getCount() {
                return 10;
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
