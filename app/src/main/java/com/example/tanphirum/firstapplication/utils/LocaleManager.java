package com.example.tanphirum.firstapplication.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by Tan Phirum on 3/2/18.
 */
public class LocaleManager {

    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_KHMER = "km";
    private static final String LANGUAGE_KEY = "language_key";

    public static Context setLocale(Context c) {
        return updateResources(c, getLanguage(c));
    }

    public static Context setNewLocale(Context c, String language) {
        persistLanguage(c, language);
        return updateResources(c, language);
    }

    public static String getLanguage(Context c) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        return prefs.getString(LANGUAGE_KEY, LANGUAGE_ENGLISH);
    }

    private static void persistLanguage(Context c, String language) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        // use commit() instead of apply(), because sometimes we kill the application process immediately
        // which will prevent apply() to finish
        prefs.edit().putString(LANGUAGE_KEY, language).commit();
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }

    /**
     * Start get a specific string resource
     *
     * @param context
     * @param resId
     * @param locale
     * @return
     */
    @NonNull
    public static String getStringByLocal(Activity context, int resId, String locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            return getStringByLocalPlus17(context, resId, locale);
        else
            return getStringByLocalBefore17(context, resId, locale);
    }

    public static String[] getStringArrayByLocal(Activity context, int resId, String locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            return getStringArrayByLocalPlus17(context, resId, locale);
        else
            return getStringArrayByLocalBefore17(context, resId, locale);
    }

    @NonNull
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static String getStringByLocalPlus17(Activity context, int resId, String locale) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(new Locale(locale));
        return context.createConfigurationContext(configuration).getResources().getString(resId);
    }

    /**
     * use for array int
     */
    @NonNull
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static String[] getStringArrayByLocalPlus17(Activity context, int resId, String locale) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(new Locale(locale));
        return context.createConfigurationContext(configuration).getResources().getStringArray(resId);
    }

    private static String getStringByLocalBefore17(Context context, int resId, String language) {
        Resources currentResources = context.getResources();
        AssetManager assets = currentResources.getAssets();
        DisplayMetrics metrics = currentResources.getDisplayMetrics();
        Configuration config = new Configuration(currentResources.getConfiguration());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        config.locale = locale;
        /*
         * Note: This (temporarily) changes the devices locale! TODO find a
         * better way to get the string in the specific locale
         */
        Resources defaultLocaleResources = new Resources(assets, metrics, config);
        String string = defaultLocaleResources.getString(resId);
        // Restore device-specific locale
        new Resources(assets, metrics, currentResources.getConfiguration());
        return string;
    }

    /**
     * use for array int
     */
    private static String[] getStringArrayByLocalBefore17(Context context, int resId, String language) {
        Resources currentResources = context.getResources();
        AssetManager assets = currentResources.getAssets();
        DisplayMetrics metrics = currentResources.getDisplayMetrics();
        Configuration config = new Configuration(currentResources.getConfiguration());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        config.locale = locale;
        /*
         * Note: This (temporarily) changes the devices locale! TODO find a
         * better way to get the string in the specific locale
         */
        Resources defaultLocaleResources = new Resources(assets, metrics, config);
        String[] string = defaultLocaleResources.getStringArray(resId);
        // Restore device-specific locale
        new Resources(assets, metrics, currentResources.getConfiguration());
        return string;
    }
    //end get specific
}