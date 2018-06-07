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
    private Button mBtnRecyclerView, mBtnUIKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNewIntent = findViewById(R.id.btn_new_intent);
        mBtnRecyclerView = findViewById(R.id.btn_recyclerview);
        mBtnUIKit = findViewById(R.id.btn_uikit);

        mBtnNewIntent.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnUIKit.setOnClickListener(this);
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
        }
    }

    private void intent(Context context, Class clsName) {
        startActivity(new Intent(context, clsName));
    }
}
