package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tanphirum.firstapplication.adapter.LoadAdapter;
import com.example.tanphirum.firstapplication.callback.EndlessRecyclerViewScrollListener;

import java.util.LinkedList;

public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRcv;
    private LoadAdapter adapter;
    private LinkedList<String> mLinkedList;

    private EndlessRecyclerViewScrollListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        mLinkedList = new LinkedList<>();

        mRcv = findViewById(R.id.rcv);

        adapter = new LoadAdapter(this, createList(20));

        mRcv.setAdapter(adapter);

        //mRcv.setLayoutManager(new LinearLayoutManager(this));
        //mRcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //mRcv.setLayoutManager(new GridLayoutManager(this, 3));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //mRcv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        mRcv.setLayoutManager(linearLayoutManager);

        /*SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRcv);*/

        mListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int currentSize = adapter.getItemCount();
                        for (int i=0; i < 10; i++) {
                            adapter.insertItem("item " + adapter.getItemCount() + i);
                        }
                        adapter.notifyItemRangeChanged(currentSize, adapter.getItemCount() - 1);
                    }
                }, 500);

            }
        };

        mRcv.addOnScrollListener(mListener);
    }

    private Handler mHandler = new Handler();

    private LinkedList<String> createList(int amount) {
        for (int i=0; i < amount; i++) {
            mLinkedList.add("item " + i);
        }
        return mLinkedList;
    }
}
