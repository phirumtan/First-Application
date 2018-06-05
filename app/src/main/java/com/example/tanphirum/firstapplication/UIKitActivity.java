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

    private TextInputLayout mTxtInputUsername, mTxtInputPass, mTxtInputConPass;
    private TextInputEditText mEdtUsername;
    private String mUsername;
    private TextInputEditText mEdtPassword, mEdtConfirmPass;
    private String mPass;
    private String mConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uikit);

        mTxtInputUsername = findViewById(R.id.txtInputUsername);
        mTxtInputPass = findViewById(R.id.txtInputPass);
        mTxtInputConPass = findViewById(R.id.txtInputConfirmPass);
        mEdtUsername = findViewById(R.id.edt_username);
        mEdtPassword = findViewById(R.id.edt_pass);
        mEdtConfirmPass = findViewById(R.id.edt_confirm_pass);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mUsername = mEdtUsername.getText().toString().trim();
                mPass = mEdtPassword.getText().toString().trim();
                mConfirmPass = mEdtConfirmPass.getText().toString().trim();
                if (TextUtils.isEmpty(mUsername)) {
                    mTxtInputUsername.setError("Input username");
                    mEdtUsername.requestFocus();
                } else if (TextUtils.isEmpty(mPass)) {
                    mTxtInputPass.setError("Input password");
                    mEdtPassword.requestFocus();
                } else if (TextUtils.isEmpty(mConfirmPass)) {
                    mTxtInputConPass.setError("Input confirm password");
                    mEdtConfirmPass.requestFocus();
                } else if (!mPass.equals(mConfirmPass)) {
                    Toast.makeText(v.getContext(), "password and confirm password must be the same.", Toast.LENGTH_SHORT).show();
                } else {
                    mTxtInputUsername.setError(null);
                    mTxtInputPass.setError(null);
                    mTxtInputConPass.setError(null);
                    new AlertDialog.Builder(v.getContext())
                            .setCancelable(false)
                            .setMessage("are you sure to register?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(v.getContext(), "register " + mUsername + " successful", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .create().show();
                }
            }
        });
    }

    private boolean validateView(TextInputEditText v, String value) {
        if (TextUtils.isEmpty(value.trim())) {
            v.setError("view can not null");
            v.requestFocus();
            return false;
        } else {
            v.setError(null);
            return true;
        }

    }


}
