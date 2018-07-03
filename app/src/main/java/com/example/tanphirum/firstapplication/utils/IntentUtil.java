package com.example.tanphirum.firstapplication.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.tanphirum.firstapplication.BuildConfig;

import java.util.List;

/**
 * Created by Sabay Android on 26/01/2016.
 */
public class IntentUtil {

    /* Methods for open from one activity to another activity*/
    public static void startIntentOpenActivity(Context context, Class<?> cls) {
        startIntentOpenActivity(context, cls, Intent.FLAG_ACTIVITY_CLEAR_TOP, false);
    }

    public static void startIntentOpenActivity(Context context, Class<?> cls, boolean finish) {
        startIntentOpenActivity(context, cls, Intent.FLAG_ACTIVITY_CLEAR_TOP, finish);
    }

    public static void startIntentOpenActivity(Context context, Class<?> cls, int flag) {
        startIntentOpenActivityWithExtra(context, cls, flag, false, null);
    }

    public static void startIntentOpenActivity(Context context, Class<?> cls, int flag, boolean finish) {
        startIntentOpenActivityWithExtra(context, cls, flag, finish, null);
    }

    private static void startIntentOpenActivityWithExtra(Context context, Class<?> cls, int flag, boolean finish, Bundle args) {
        if (context == null) return;
        Intent intent = new Intent(context, cls);
        if (args != null)
            intent.putExtras(args);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        if (flag > 0) intent.addFlags(flag);
        context.startActivity(intent);
        if (context instanceof Activity)
            if (finish) ((Activity) context).finish();
    }

    public static void startIntentOpenActivityWithExtra(Context context, Class<?> cls, String key, String value) {
        if (context == null) return;
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    public static void startIntentOpenActivityWithExtra(Context context, Class<?> cls, Bundle args) {
        startIntentOpenActivityWithExtra(context, cls, args, false);
    }

    public static void startIntentOpenActivityWithExtra(Context context, Class<?> cls, Bundle args, boolean finish) {
        startIntentOpenActivityWithExtra(context, cls, args, Intent.FLAG_ACTIVITY_CLEAR_TOP, finish);
    }

    public static void startIntentOpenActivityWithExtra(Context context, Class<?> cls, Bundle args, int flag) {
        startIntentOpenActivityWithExtra(context, cls, args, flag, false);
    }

    private static void startIntentOpenActivityWithExtra(Context context, Class<?> cls, Bundle args, int flag, boolean finish) {
        if (context == null) return;
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        if (flag > 0) intent.addFlags(flag);
        if (args != null)
            intent.putExtras(args);
        context.startActivity(intent);
        if (context instanceof Activity)
            if (finish) ((Activity) context).finish();
    }

    public static void startIntentOpenActivityForResult(Context context, Class<?> cls, int request_code) {
        startIntentOpenActivityForResultWithExtra(context, cls, null, Intent.FLAG_ACTIVITY_CLEAR_TOP, request_code);
    }

    public static void startIntentOpenActivityForResultWithExtra(Context context, Class<?> cls, Bundle args, int request_code) {
        startIntentOpenActivityForResultWithExtra(context, cls, args, Intent.FLAG_ACTIVITY_CLEAR_TOP, request_code);
    }

    public static void startIntentOpenActivityForResultWithExtra(Context context, Class<?> cls, Bundle args, int request_code, boolean finish) {
        startIntentOpenActivityForResultWithExtra(context, cls, args, Intent.FLAG_ACTIVITY_CLEAR_TOP, request_code, finish);
    }

    public static void startIntentOpenActivityForResultWithExtra(Context context, Class<?> cls, Bundle args, int flag, int request_code) {
        startIntentOpenActivityForResultWithExtra(context, cls, args, flag, request_code, false);
    }

    public static void startIntentOpenActivityForResultWithExtra(Context context, Class<?> cls, Bundle args, int flag, int request_code, boolean finish) {
        if (context == null) return;
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        if (flag > 0) intent.addFlags(flag);
        if (args != null)
            intent.putExtras(args);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, request_code);
            if (finish)
                ((Activity) context).finish();
        }
    }

    /* Methods for open from service*/
    public static void startIntentServiceWithActionAndBundle(Activity context, Class cls, String action, Bundle args) {
        startIntentService(context, cls, action, args);
    }

    private static void startIntentService(Activity context, Class<?> cls, String action, Bundle args) {
        if (context == null) return;
        try {
            Intent intentService = new Intent(context, cls);
            intentService.setAction(action);
            if (args != null) {
                intentService.putExtras(args);
            }
            context.startService(intentService);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* Methods for open link to browser app in phone*/
    public static void startIntentView(Activity activity, String link) {
        if (activity == null) return;
        if (!TextUtils.isEmpty(link)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            List<ResolveInfo> listHandleIntent = activity.getPackageManager().queryIntentActivities(intent, 0);
            if (listHandleIntent.size() > 0) {
                activity.startActivity(intent);
                activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }
    }


    public static void startIntentOpenStore(Context context) {
        if (context == null) return;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID));
            context.startActivity(intent);
        } catch (Exception e) { //google play app is not installed
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setData(Uri.parse(Globals.getAppLinkOnStore(context)));
            context.startActivity(intent);
        }
    }

    @Nullable
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }

    public static void restartActivityWithNoAnim(Activity activity) {
        Intent intent = activity.getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        activity.finish();
        activity.overridePendingTransition(0, 0);
        activity.startActivity(intent);
    }

    public static void openSettings(Activity activity) {
        openSettings(activity, 0);
    }

    public static void openSettings(Activity activity, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (requestCode == 0)
            activity.startActivity(intent);
        else activity.startActivityForResult(intent, requestCode);
    }
}

