package com.example.tanphirum.firstapplication.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tanphirum.firstapplication.R;
import com.example.tanphirum.firstapplication.db.UserDbRepo;
import com.example.tanphirum.firstapplication.pojo.Datum;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SqliteFragment extends Fragment {

    public static final String TAG = SqliteFragment.class.getSimpleName();

    @BindView(R.id.img_profile)
    ImageView mImgProfile;
    @BindView(R.id.container)
    View mContainer;

    private AlertDialog mAlertDialog;

    private UserDbRepo mUserDbRepo;

    private FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sqlite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        manager = getFragmentManager();

        mUserDbRepo = new UserDbRepo(view.getContext());
    }

    @OnClick({R.id.img_profile, R.id.btn_register})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.img_profile:
                if (mAlertDialog == null) {
                    mAlertDialog = new AlertDialog.Builder(v.getContext())
                            .setTitle("Select your profile")
                            .setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create();
                }
                mAlertDialog.setCancelable(false);
                mAlertDialog.show();
                break;
            case R.id.btn_register:
                int random = new Random().nextInt(100);
                Datum datum = new Datum();
                datum.withFirstName("phirum_" + random);
                datum.withPassword("123_" + random);
                if (mUserDbRepo.insertDataToTable(datum) > 0) {
                    manager.beginTransaction().replace(R.id.container, new SqliteListUserFragment(), SqliteListUserFragment.TAG).commit();
                    mContainer.setVisibility(View.VISIBLE);
                    Toast.makeText(v.getContext(), "insert success", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(v.getContext(), "insert fail", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
