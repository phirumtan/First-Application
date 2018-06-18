package com.example.tanphirum.firstapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.tanphirum.firstapplication.task.AsyncTaskLoader;

import org.json.JSONObject;

public class AsyncTaskLoaderActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String>{

    private EditText mEdtUsername;
    private Button mBtnGetData;

    private View mViewProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_loader);

        mEdtUsername = findViewById(R.id.edt_username);

        mViewProgress = findViewById(R.id.view_progress);

        mBtnGetData = findViewById(R.id.btn_get_data);
        mBtnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide the keyboard when the button is pushed.
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager != null) {
                    inputManager.hideSoftInputFromWindow(mEdtUsername.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                // Check the status of the network connection.
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connMgr != null)
                    networkInfo = connMgr.getActiveNetworkInfo();

                // If the network is active and the search field is not empty,
                // add the search term to the arguments Bundle and start the loader.
                if (networkInfo != null && networkInfo.isConnected()) {
                    mViewProgress.setVisibility(View.VISIBLE);
                    getSupportLoaderManager().restartLoader(0, null, AsyncTaskLoaderActivity.this);
                }
            }
        });
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    /**
     * Loader Callbacks
     */

    /**
     * The LoaderManager calls this method when the loader is created.
     *
     * @param id ID integer to id   entify the instance of the loader.
     * @param args The bundle that contains the search parameter.
     * @return Returns a new BookLoader containing the search term.
     */
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader(this);
    }

    /**
     * Called when the data has been loaded. Gets the desired information from
     * the JSON and updates the Views.
     *
     * @param loader The loader that has finished.
     * @param data The JSON response from the Books API.
     */
    @NonNull
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        mViewProgress.setVisibility(View.GONE);
        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(data);

            if (jsonObject.optBoolean("status")) {
                JSONObject responseObject = jsonObject.optJSONObject("response");
                String username = responseObject.optString("fullname");
                if (!TextUtils.isEmpty(username)) {
                    mEdtUsername.setText(username);
                } else {
                    mEdtUsername.setText("can not get username from server");
                }
            } else
                mEdtUsername.setText("something wrong with return json");

        } catch (Exception e){
            mEdtUsername.setText("error with case object from server");
            e.printStackTrace();
        }
    }

    /**
     * In this case there are no variables to clean up when the loader is reset.
     *
     * @param loader The loader that was reset.
     */
    @Override
    public void onLoaderReset(Loader<String> loader) {}
}
