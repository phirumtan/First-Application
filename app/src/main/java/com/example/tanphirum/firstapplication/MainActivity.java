package com.example.tanphirum.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXT_KEY_MSG = "ext_key_msg";

    private EditText mEdtMsg;
    private Button mBtnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtMsg = findViewById(R.id.edt_enter_msg);

        mBtnSend = findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);

    }

    public void sendMessage(View v) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXT_KEY_MSG, mEdtMsg.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage(v);
                    }
                }, 3000);

                break;
        }
    }
}
