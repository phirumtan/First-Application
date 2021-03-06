package com.example.tanphirum.firstapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanphirum.firstapplication.MainActivity;
import com.example.tanphirum.firstapplication.R;
import com.example.tanphirum.firstapplication.db.UserDbRepo;
import com.example.tanphirum.firstapplication.holder.UpdateUserHolder;
import com.example.tanphirum.firstapplication.pojo.Datum;

import java.util.LinkedList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    private LayoutInflater mInflater;

    public LinkedList<Datum> mListItem;

    public MyListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mListItem = new LinkedList<>();
    }

    @NonNull
    @Override
    public MyListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.layout_user_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.MyViewHolder holder, int position) {
        Datum item = mListItem.get(position);
        holder.mTxtId.setText("ID: " + item.id);
        holder.mTxtItemName.setText("Name:" + item.firstName);
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public void insertItem(Datum s) {
        mListItem.add(s);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImgIcon;
        private TextView mTxtItemName, mTxtId;
        private AppCompatButton mBtnEdit, mBtnDelete;
        private MyListAdapter adapter;

        private MyViewHolder(View itemView, MyListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;

            mTxtId = itemView.findViewById(R.id.txt_id);
            mImgIcon = itemView.findViewById(R.id.img_user);
            mTxtItemName = itemView.findViewById(R.id.txt_username);
            mBtnDelete = itemView.findViewById(R.id.btn_delete);
            mBtnEdit = itemView.findViewById(R.id.btn_edit);

            mBtnEdit.setOnClickListener(this);
            mBtnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = this.getLayoutPosition();
            switch (v.getId()) {
                case R.id.btn_delete:
                    UserDbRepo dbRepo = new UserDbRepo(v.getContext());
                    if (dbRepo.deleteDataFromTable(mListItem.get(pos).id) > 0) {
                        Toast.makeText(v.getContext(), "delete success", Toast.LENGTH_SHORT).show();
                        mListItem.remove(pos);
                        notifyItemRangeRemoved(pos, getItemCount());
                    } else
                        Toast.makeText(v.getContext(), "delete fail", Toast.LENGTH_SHORT).show();
                    Datum datum = new Datum();
                    Intent aaaa = new Intent(v.getContext(), MainActivity.class);
                    aaaa.putExtra("", datum);
                    v.getContext().startActivity(aaaa);

                    break;
                case R.id.btn_edit:
                    UserDbRepo dbRepo1 = new UserDbRepo(v.getContext());
                    UpdateUserHolder holder = new UpdateUserHolder(v.getContext(), dbRepo1, mListItem.get(pos));
                    AlertDialog dialog = new AlertDialog.Builder(v.getContext())
                            .setView(holder.v)
                            .create();
                    holder.setAdapter(adapter);
                    holder.setDialog(dialog);
                    holder.setPos(pos);
                    dialog.setCancelable(false);
                    dialog.show();
                    break;
            }
        }
    }

}
