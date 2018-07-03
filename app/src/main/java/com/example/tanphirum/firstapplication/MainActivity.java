package com.example.tanphirum.firstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tanphirum.firstapplication.fragment.FCMFragment;
import com.example.tanphirum.firstapplication.fragment.SqliteFragment;
import com.example.tanphirum.firstapplication.utils.IntentUtil;
import com.example.tanphirum.firstapplication.utils.LocaleManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName(); // constant tag log message
    private static final String CHANNEL_ID = "channel_01";
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mBtnNewIntent;
    private Button mBtnRecyclerView, mBtnUIKit, mBtnViewPager, mBtnWebview, mBtnAsync;

    private View main;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // [START handle_data_extras]
        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {
                for (String key : getIntent().getExtras().keySet()) {
                    Object value = getIntent().getExtras().get(key);
                    Log.d(TAG, "Key: " + key + " Value: " + value);
                }
            }
        }

        main = findViewById(R.id.view_container);
        manager = getSupportFragmentManager();

        mBtnNewIntent = findViewById(R.id.btn_new_intent);
        mBtnRecyclerView = findViewById(R.id.btn_recyclerview);
        mBtnUIKit = findViewById(R.id.btn_uikit);
        mBtnViewPager = findViewById(R.id.btn_ui_viewpager);
        mBtnWebview = findViewById(R.id.btn_webview);
        mBtnAsync = findViewById(R.id.btn_asyn);


        mBtnNewIntent.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnUIKit.setOnClickListener(this);
        mBtnViewPager.setOnClickListener(this);
        mBtnWebview.setOnClickListener(this);
        mBtnAsync.setOnClickListener(this);
        findViewById(R.id.btn_asyn_loader).setOnClickListener(this);
        findViewById(R.id.btn_retrofit).setOnClickListener(this);
        findViewById(R.id.btn_broadcast).setOnClickListener(this);
        findViewById(R.id.btn_notification).setOnClickListener(this);
        findViewById(R.id.btn_shared_preference).setOnClickListener(this);
        findViewById(R.id.btn_sqlite_activity).setOnClickListener(this);
        findViewById(R.id.btn_fcm).setOnClickListener(this);
        findViewById(R.id.btn_ml_kit_qr_code).setOnClickListener(this);
        findViewById(R.id.btn_maps).setOnClickListener(this);
        findViewById(R.id.btn_scan_qr_code).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_intent:
                intent(v.getContext(), NewIntentActivity.class);
                break;
            case R.id.btn_recyclerview:
                intent(v.getContext(), RecycleViewActivity.class);
                break;
            case R.id.btn_uikit:
                intent(v.getContext(), UIKitActivity.class);
                break;
            case R.id.btn_ui_viewpager:
                intent(v.getContext(), ViewPagerActivity.class);
                break;
            case R.id.btn_webview:
                intent(v.getContext(), WebviewActivity.class);
                break;
            case R.id.btn_asyn:
                intent(v.getContext(), AsyncTaskActivity.class);
                break;
            case R.id.btn_asyn_loader:
                intent(v.getContext(), AsyncTaskLoaderActivity.class);
                break;
            case R.id.btn_retrofit:
                intent(v.getContext(), Retrofit2Activity.class);
                break;
            case R.id.btn_broadcast:
                intent(v.getContext(), BroadcastActivity.class);
                break;
            case R.id.btn_notification:
                intent(v.getContext(), NotificationActivity.class);
                break;
            case R.id.btn_shared_preference:
                intent(v.getContext(), SharedPreferenceActivity.class);
                break;
            case R.id.btn_sqlite_activity:
                if (main != null) {
                    if (manager.findFragmentByTag(SqliteFragment.TAG) == null)
                        manager.beginTransaction().replace(R.id.view_container, new SqliteFragment(), SqliteFragment.TAG).commit();
                    //https://developer.android.com/training/multiscreen/screensizes

                } else
                    intent(v.getContext(), SqliteActivity.class);
                break;
            case R.id.btn_fcm:
                if (main != null) {
                    if (manager.findFragmentByTag(FCMFragment.TAG) == null)
                        manager.beginTransaction().replace(R.id.view_container, new FCMFragment(), FCMFragment.TAG).commit();
                } else
                    intent(v.getContext(), FCMActivity.class);
                break;
            case R.id.btn_ml_kit_qr_code:
                if (main != null) {
                    if (manager.findFragmentByTag(FCMFragment.TAG) == null)
                        manager.beginTransaction().replace(R.id.view_container, new FCMFragment(), FCMFragment.TAG).commit();
                } else
                    intent(v.getContext(), MLKitScanQRcodeActivity.class);
                break;
            case R.id.btn_maps:
                /*if (main != null) {
                    if (manager.findFragmentByTag(FCMFragment.TAG) == null)
                        manager.beginTransaction().replace(R.id.view_container, new FCMFragment(), FCMFragment.TAG).commit();
                } else*/
                intent(v.getContext(), MapsActivity.class);
                break;
            case R.id.btn_scan_qr_code:
                intent(v.getContext(), ScanQrActivity.class);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Apps apps = (Apps) getApplication();
        switch (item.getItemId()) {
            case R.id.action_english:
                apps.setLanguage(LocaleManager.LANGUAGE_ENGLISH);
                IntentUtil.restartActivityWithNoAnim(this);
                break;
            case R.id.action_khmer:
                apps.setLanguage(LocaleManager.LANGUAGE_KHMER);
                IntentUtil.restartActivityWithNoAnim(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.setLocale(newBase));
    }

    private void intent(Context context, Class clsName) {
        startActivity(new Intent(context, clsName));
    }
}
