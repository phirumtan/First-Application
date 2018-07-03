package com.example.tanphirum.firstapplication.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tanphirum.firstapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FCMFragment extends Fragment {

    public static final String TAG = FCMFragment.class.getSimpleName();

    public static final String EXT_KEY_S = "ext_key_s";

    @BindView(R.id.edt_subscribe)
    TextInputEditText mEdtSubscribe;

    @BindView(R.id.btn_show_token)
    Button mBtnShowToken;

    @BindView(R.id.btn_subscribe)
    Button mBtnSubscribe;


    public FCMFragment() {
        super();
    }

    public FCMFragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putString(EXT_KEY_S, s);
        FCMFragment f = new FCMFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fcm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    view.getContext().getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                        channelName, NotificationManager.IMPORTANCE_LOW));
            }
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getActivity() != null && getActivity().getIntent() != null) {
            if (getActivity().getIntent().getExtras() != null) {
                for (String key : getActivity().getIntent().getExtras().keySet()) {
                    Object value = getActivity().getIntent().getExtras().get(key);
                    Log.d(TAG, "Key: " + key + " Value: " + value);
                }
            }
        }
        // [END handle_data_extras]
    }

    @OnClick({R.id.btn_show_token, R.id.btn_subscribe})
    public void onClickListener(final View v) {
        switch (v.getId()) {
            case R.id.btn_show_token:
                // Get token
                String token = FirebaseInstanceId.getInstance().getToken();

                // Log and toast
                String msg = getString(R.string.msg_token_fmt, token);
                Log.d(TAG, msg);
                Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_subscribe:
                Log.d(TAG, "Subscribing to news topic");
                // [START subscribe_topics]
                FirebaseMessaging.getInstance().subscribeToTopic("news")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = getString(R.string.msg_subscribed);
                                if (!task.isSuccessful()) {
                                    msg = getString(R.string.msg_subscribe_failed);
                                }
                                Log.d(TAG, msg);
                                Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                // [END subscribe_topics]
                break;
        }
    }
}
