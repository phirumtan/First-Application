package com.example.tanphirum.firstapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanphirum.firstapplication.R;

import java.util.LinkedList;

public class LoadAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInflater;

    private LinkedList<String> mListItem;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    public LoadAdapter(Context context, LinkedList<String> listItem) {
        this.mInflater = LayoutInflater.from(context);
        this.mListItem = listItem;
    }

    @Override
    public int getItemViewType(int position) {
        if (mListItem.size() > 0 && mListItem.size() - 1 == position) {
            return VIEW_PROG;
        }
        return VIEW_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder v = null;
        if (viewType == VIEW_ITEM) {
            v = new MyViewHolder(mInflater.inflate(R.layout.layout_list_item, parent, false));
        } else {
            v = new MyProgressViewHolder(mInflater.inflate(R.layout.layout_progress_bar, parent, false));
        }
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mTxtItemName.setText(mListItem.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public void insertItem(String s) {
        mListItem.add(s);
    }

    static class MyProgressViewHolder extends RecyclerView.ViewHolder {
        public MyProgressViewHolder(View itemView) {
            super(itemView);
        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImgIcon;
        private TextView mTxtItemName;

        public MyViewHolder(View itemView) {
            super(itemView);

            mImgIcon = itemView.findViewById(R.id.img_icon);
            mTxtItemName = itemView.findViewById(R.id.txt_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mTxtItemName.setText("click " + mTxtItemName.getText());
        }
    }

}
