package com.example.tanphirum.firstapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class UIKitActivity extends AppCompatActivity {

    private TextInputLayout mTxtInputUsername;
    private TextInputEditText mEdtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uikit);

        mTxtInputUsername = findViewById(R.id.txtInputUsername);
        mEdtUsername = findViewById(R.id.edt_username);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String username = mEdtUsername.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    mTxtInputUsername.setError("username can not null");
                } else {
                    mTxtInputUsername.setError(null);
                    new AlertDialog.Builder(v.getContext())
                            .setMessage("are you sure to register?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(v.getContext(), "register " + username + " successful", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .create().show();
                }
            }
        });
    }
}
