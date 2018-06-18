package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tanphirum.firstapplication.fragment.SimpleViewPagerFragment;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager manager;

    private Button mBtnSimpelViewPager, mBtnCustomViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mBtnSimpelViewPager = findViewById(R.id.btn_simple_view_pager);
        mBtnCustomViewPager = findViewById(R.id.btn_custom_view_pager);

        manager = getSupportFragmentManager();

        mBtnSimpelViewPager.setOnClickListener(this);
        mBtnCustomViewPager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simple_view_pager:
                addFragment(SimpleViewPagerFragment.newInstance(), "simpleView");
                break;
            case R.id.btn_custom_view_pager:
                break;

        }
    }

    public void addFragment(Fragment f, String tag) {
        if (manager.findFragmentByTag(tag) == null) {
            manager.beginTransaction().replace(R.id.container, f).addToBackStack(tag).commitAllowingStateLoss();
        }
    }
}
