package com.example.tanphirum.firstapplication.task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class StaticAsync extends AsyncTask<Void, Integer, String> {

    // The TextView where we will show results
    private TextView mTextView;

    private View mViewPro;

    // Constructor that provides a reference to the TextView from the MainActivity
    public StaticAsync(TextView tv, View viewPro) {
        mTextView = tv;
        mViewPro = viewPro;
    }

    @Override
    protected void onPreExecute() {
        mViewPro.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("phirum", "values = " +  values.toString());
    }

    /**
     * Runs on the background thread.
     *
     * @param voids No parameters in this use case.
     * @return Returns the string including the amount of time that
     * the background thread slept.
     */
    @Override
    protected String doInBackground(Void... voids) {

        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 2000;

        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }



    /**
     * Does something with the result on the UI thread; in this case
     * updates the TextView.
     */
    @Override
    protected void onPostExecute(String result) {
        mViewPro.setVisibility(View.GONE);
        mTextView.setText(result);
    }
}