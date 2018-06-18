package com.example.tanphirum.firstapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanphirum.firstapplication.R;
import com.example.tanphirum.firstapplication.adapter.MyPageAdapter;

public class SimpleViewPagerFragment extends Fragment{

    private ViewPager mPager;
    private MyPageAdapter mPagerAdapter;
    private TabLayout mPagerTab;

    public SimpleViewPagerFragment() {
        super();
    }

    public static SimpleViewPagerFragment newInstance() {
        SimpleViewPagerFragment f = new SimpleViewPagerFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mPagerTab = view.findViewById(R.id.sliding_tabs);
        mPager = view.findViewById(R.id.pager);
        mPagerAdapter = new MyPageAdapter(getChildFragmentManager(), getActivity());

        mPager.setAdapter(mPagerAdapter);
        mPagerTab = view.findViewById(R.id.sliding_tabs);
        mPagerTab.setupWithViewPager(mPager);

        for (int  i=0; i < mPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = mPagerTab.getTabAt(i);
            tab.setCustomView(mPagerAdapter.getTabView(i));
        }

        //mPagerTab.setTabIndicatorColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, getTheme()));
    }
}
