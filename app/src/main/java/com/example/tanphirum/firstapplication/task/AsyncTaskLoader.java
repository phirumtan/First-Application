package com.example.tanphirum.firstapplication.task;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.tanphirum.firstapplication.webservice.NetworkUtils;

public class AsyncTaskLoader extends android.support.v4.content.AsyncTaskLoader<String> {

    public AsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    /**
     * This method is invoked by the LoaderManager whenever the loader is started
     */
    @Override
    protected void onStartLoading() {
        forceLoad();// Starts the loadInBackground method
    }

    /**
     * Connects to the network and makes the Books API request on a background thread.
     *
     * @return Returns the raw JSON response from the API call.
     */
    @Override
    public String loadInBackground() {
        return NetworkUtils.getDataFromServer(null);
    }
}
