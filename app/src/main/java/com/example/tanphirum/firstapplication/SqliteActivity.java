package com.example.tanphirum.firstapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tanphirum.firstapplication.utils.IntentUtil;
import com.example.tanphirum.firstapplication.utils.LocaleManager;

public class SqliteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
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
}
