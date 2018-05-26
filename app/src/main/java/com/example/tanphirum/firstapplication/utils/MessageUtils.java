package com.example.tanphirum.firstapplication.utils;

import android.content.Context;
import android.widget.Toast;

public class MessageUtils {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
