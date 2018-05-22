package com.example.tanphirum.firstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i=0; i < 10; i++)
            Log.d("debug", "i =" + i);

    }

    public void sendMessage(View v) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

}
