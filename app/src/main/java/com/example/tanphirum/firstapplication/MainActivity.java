package com.example.tanphirum.firstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName(); // constant tag log message

    private Button mBtnNewIntent;
    private Button mBtnRecyclerView, mBtnUIKit, mBtnViewPager, mBtnWebview, mBtnAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNewIntent = findViewById(R.id.btn_new_intent);
        mBtnRecyclerView = findViewById(R.id.btn_recyclerview);
        mBtnUIKit = findViewById(R.id.btn_uikit);
        mBtnViewPager = findViewById(R.id.btn_ui_viewpager);
        mBtnWebview = findViewById(R.id.btn_webview);
        mBtnAsync = findViewById(R.id.btn_asyn);


        mBtnNewIntent.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnUIKit.setOnClickListener(this);
        mBtnViewPager.setOnClickListener(this);
        mBtnWebview.setOnClickListener(this);
        mBtnAsync.setOnClickListener(this);
        findViewById(R.id.btn_asyn_loader).setOnClickListener(this);
        findViewById(R.id.btn_retrofit).setOnClickListener(this);
        findViewById(R.id.btn_broadcast).setOnClickListener(this);
        findViewById(R.id.btn_notification).setOnClickListener(this);
        findViewById(R.id.btn_shared_preference).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_intent:
                intent(v.getContext(), NewIntentActivity.class);
                break;
            case R.id.btn_recyclerview:
                intent(v.getContext(), RecycleViewActivity.class);
                break;
            case R.id.btn_uikit:
                intent(v.getContext(), UIKitActivity.class);
                break;
            case R.id.btn_ui_viewpager:
                intent(v.getContext(), ViewPagerActivity.class);
                break;
            case R.id.btn_webview:
                intent(v.getContext(), WebviewActivity.class);
                break;
            case R.id.btn_asyn:
                intent(v.getContext(), AsyncTaskActivity.class);
                break;
            case R.id.btn_asyn_loader:
                intent(v.getContext(), AsyncTaskLoaderActivity.class);
                break;
            case R.id.btn_retrofit:
                intent(v.getContext(), Retrofit2Activity.class);
                break;
            case R.id.btn_broadcast:
                intent(v.getContext(), BroadcastActivity.class);
                break;
            case R.id.btn_notification:
                intent(v.getContext(), NotificationActivity.class);
                break;
            case R.id.btn_shared_preference:
                intent(v.getContext(), SharedPreferenceActivity.class);
                break;
        }
    }

    private void intent(Context context, Class clsName) {
        startActivity(new Intent(context, clsName));
    }
}
