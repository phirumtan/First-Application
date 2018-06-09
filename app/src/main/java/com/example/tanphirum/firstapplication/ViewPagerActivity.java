package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tanphirum.firstapplication.adapter.MyPageAdapter;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TabLayout mPagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mPagerTab = findViewById(R.id.sliding_tabs);
        mPager = findViewById(R.id.pager);
        mPagerAdapter = new MyPageAdapter(getSupportFragmentManager(), this);

        mPager.setAdapter(mPagerAdapter);
        mPagerTab.setupWithViewPager(mPager);

        /*for (int  i=0; i < mPagerAdapter.getCount(); i++) {
            mPagerTab.getTabAt(i).setIcon(R.mipmap.ic_launcher);
        }*/

        //mPagerTab.setTabIndicatorColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, getTheme()));
    }
}
