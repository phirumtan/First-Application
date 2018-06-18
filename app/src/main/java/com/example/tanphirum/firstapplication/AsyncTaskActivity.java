package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tanphirum.firstapplication.task.StaticAsync;

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TEXT_STATE = "text_state";

    private TextView mTxtLabel;
    private Button mBtnStartTask;
    private View mViewPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        mTxtLabel = findViewById(R.id.txt_label);
        mBtnStartTask = findViewById(R.id.btn_start_task);

        mViewPro = findViewById(R.id.view_progress);

        if (savedInstanceState != null)
            mTxtLabel.setText(savedInstanceState.getString(TEXT_STATE));

        mBtnStartTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_task:
                new StaticAsync(mTxtLabel, mViewPro).execute();
                break;

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTxtLabel.getText().toString());
    }
}
