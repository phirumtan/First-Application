package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.tanphirum.firstapplication.adapter.ListAdapter;

import java.util.LinkedList;

public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRcv;
    private ListAdapter adapter;
    private LinkedList<String> mLinkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        mLinkedList = new LinkedList<>();

        mRcv = findViewById(R.id.rcv);

        adapter = new ListAdapter(this, createList(10));

        mRcv.setAdapter(adapter);
        //mRcv.setLayoutManager(new LinearLayoutManager(this));
        //mRcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //mRcv.setLayoutManager(new GridLayoutManager(this, 3));

        mRcv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRcv);
    }

    private LinkedList<String> createList(int amount) {
        for (int i=0; i < amount; i++) {
            mLinkedList.add("item " + i);
        }
        return mLinkedList;
    }
}
