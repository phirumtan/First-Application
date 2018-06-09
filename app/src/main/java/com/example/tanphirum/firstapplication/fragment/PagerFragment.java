package com.example.tanphirum.firstapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanphirum.firstapplication.R;

public class PagerFragment extends Fragment {

    public static final String EXT_KEY_NAME = "ext_key_name";

    public PagerFragment() {
        super();
    }

    public static PagerFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString(EXT_KEY_NAME, param);
        PagerFragment f = new PagerFragment();
        f.setArguments(args);
        return f;
    }

    private TextView mTxtName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTxtName = view.findViewById(R.id.txt_name);
        setName(getArguments().getString(EXT_KEY_NAME));
    }

    private void setName(String name) {
        mTxtName.setText(name);
    }
}
