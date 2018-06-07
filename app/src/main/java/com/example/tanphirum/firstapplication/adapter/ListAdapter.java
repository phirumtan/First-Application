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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private LayoutInflater mInflater;

    private LinkedList<String> mListItem;

    public ListAdapter(Context context, LinkedList<String> listItem) {
        this.mInflater = LayoutInflater.from(context);
        this.mListItem = listItem;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.layout_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {
        holder.mTxtItemName.setText(mListItem.get(position));
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
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
