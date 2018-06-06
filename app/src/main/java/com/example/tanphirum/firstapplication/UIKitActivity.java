package com.example.tanphirum.firstapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tanphirum.firstapplication.fragment.DatePickerFragment;

public class UIKitActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextInputLayout mTxtInputUsername, mTxtInputPass, mTxtInputConPass;
    private TextInputEditText mEdtUsername;
    private String mUsername;
    private TextInputEditText mEdtPassword, mEdtConfirmPass;
    private String mPass;
    private String mConfirmPass;
    private Spinner mSpinnerPhone;

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

        mSpinnerPhone = findViewById(R.id.sp_phone);
        mSpinnerPhone.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array_phone, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerPhone.setAdapter(adapter);

        /*findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
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
        });*/

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerFragment().show(getSupportFragmentManager(), "dd");
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "item click is " + parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void handleDatePickerClick(int year, int month, int day) {
        // The month integer returned by the date picker starts counting at 0
        // for January, so you need to add 1 to show months starting at 1.
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        // Assign the concatenated strings to dateMessage.
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        Toast.makeText(this, "Date: " + dateMessage, Toast.LENGTH_SHORT).show();
    }

    public void handleTimePickerClick(int hourOfDay, int minute) {
        // Convert time elements into strings.
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        // Assign the concatenated strings to timeMessage.
        String timeMessage = (hour_string + ":" + minute_string);
        Toast.makeText(this, "Time: " + timeMessage, Toast.LENGTH_SHORT).show();
    }
}
