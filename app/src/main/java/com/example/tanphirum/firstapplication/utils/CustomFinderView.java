package com.example.tanphirum.firstapplication.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

/**
 * Created by Tan Phirum on 3/12/18.
 */
public class CustomFinderView extends MyViewFinderView {

    public CustomFinderView(Context context) {
        super(context);
        init();
    }

    public CustomFinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLaserEnabled(true);
        setLaserColor(Color.RED);
    }
}