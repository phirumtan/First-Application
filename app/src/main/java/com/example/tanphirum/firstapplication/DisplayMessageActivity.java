package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    private TextView mTxtMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_msg);

        mTxtMsg = findViewById(R.id.txtMsg);

        if (getIntent().getExtras() != null) {
            mTxtMsg.setText(getIntent().getExtras().getString(MainActivity.EXT_KEY_MSG));
        } else {
            mTxtMsg.setText("something wrong with bundle key");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
