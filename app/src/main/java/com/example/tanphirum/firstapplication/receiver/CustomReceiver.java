package com.example.tanphirum.firstapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.tanphirum.firstapplication.BuildConfig;
import com.example.tanphirum.firstapplication.R;

/**
 * Broadcast Receiver implementation that delivers a custom Toast
 * message when it receives any of the registered broadcasts
 */
public class CustomReceiver extends BroadcastReceiver {

    //String constant that defines the custom Broadcast Action
    public static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";


    //Empty constructor
    public CustomReceiver() {
    }


    /**
     * This method gets called when the Broadcast Receiver receives a broadcast that
     * it is registered for.
     *
     * @param context The context of the application when the broadcast is received.
     * @param intent The broadcast is delivered in the form of an intent which contains
     *               the broadcast action.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        String toastMessage = null;
        if (intentAction == null) return;
        switch (intentAction){
            case Intent.ACTION_POWER_CONNECTED:
                toastMessage = context.getString(R.string.power_connected);
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                toastMessage = context.getString(R.string.power_disconnected);
                break;
            case ACTION_CUSTOM_BROADCAST:
                toastMessage = context.getString(R.string.custom_broadcast_toast);
                break;
        }

        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }

}