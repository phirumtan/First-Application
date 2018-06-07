package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        mRcv.setLayoutManager(new LinearLayoutManager(this));


    }

    private LinkedList<String> createList(int amount) {
        for (int i=0; i < amount; i++) {
            mLinkedList.add("item " + i);
        }
        return mLinkedList;
    }
}
