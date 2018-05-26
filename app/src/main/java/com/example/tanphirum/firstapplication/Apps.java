package com.example.tanphirum.firstapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class Apps extends Application implements Application.ActivityLifecycleCallbacks{

    /* Manages the state of opened vs closed activities, should be 0 or 1.
     * It will be 2 if this value is checked between activity B onStart() and
     * activity A onStop().
     * It could be greater if the top activities are not fullscreen or have
     * transparent backgrounds.
     */
    private static int visibleActivityCount = 0;
    /**
     * Manages the state of opened vs closed activities, should be 0 or 1
     * because only one can be in foreground at a time. It will be 2 if this
     * value is checked between activity B onResume() and activity A onPause().
     */
    private static int foregroundActivityCount = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        visibleActivityCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        foregroundActivityCount++;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        foregroundActivityCount--;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        visibleActivityCount--;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
