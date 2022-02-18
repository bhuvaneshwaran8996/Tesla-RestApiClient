package com.bhuvaneswaran.simple_apiclient_latest.ui.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.bhuvaneswaran.simple_apiclient_latest.R;
import com.bhuvaneswaran.simple_apiclient_latest.controls.TextViewRegular;
import com.bhuvaneswaran.simple_apiclient_latest.ui.rest.RestActivity;

import javax.inject.Inject;

public class SettingsActivity extends AppCompatActivity  {


    @Inject
    RestActivity restActivity;
    SharedPreferences prefs;
    TextViewRegular txtTimeout;
    int txtconnectTimout;
    SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // getSupportActionBar().setHomeButtonEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtTimeout = findViewById(R.id.timeoutseconds);


        onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                if(key.equalsIgnoreCase("timeout")){

                    int anInt = sharedPreferences.getInt(key, 20);
                    txtTimeout.setText("After "+anInt+" seconds");
                    txtconnectTimout = anInt;


                }
            }
        };


        try {
            txtconnectTimout = getApplicationContext().getSharedPreferences("RestPreference", MODE_PRIVATE).getInt("timeout", 20);
            getApplicationContext().getSharedPreferences("RestPreference", MODE_PRIVATE).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onBackPressed();
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        txtTimeout.setText("After "+txtconnectTimout+" seconds");


        findViewById(R.id.lnr_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment settingsFragment = SettingsFragment.newInstance(txtconnectTimout);
                settingsFragment.show(getSupportFragmentManager(), "SettingsFragment");

            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().getSharedPreferences("RestPreference", MODE_PRIVATE).unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}