package com.example.android_acquaintance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

public class SettingsActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener, Constants {
    private SwitchCompat themeSwitch;
    private int codeStyle = 0;
    private LinearLayout settingsActivityLayout;
    private TextView settingsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme());
        setContentView(R.layout.activity_settings);
        initView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_SETTINGS_SCREEN, themeSwitch.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        themeSwitch.setChecked(savedInstanceState.getBoolean(KEY_SETTINGS_SCREEN));
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private void initView() {
        settingsActivityLayout = findViewById(R.id.settings_activity_layout);
        themeSwitch = findViewById(R.id.settings_switch);
        settingsName = findViewById(R.id.settings_name);
        themeSwitch.setOnCheckedChangeListener(this);
        themeSwitch.setChecked(codeStyle == My_CALCULATOR_STYLE_DARK);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            codeStyle = My_CALCULATOR_STYLE_DARK;
            makeDarkTheme();
        } else {
            codeStyle = MY_CALCULATOR_STYLE;
            makeLightTheme();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intentResult = new Intent();
        intentResult.putExtra(APP_THEME, codeStyle);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    //получение настроек (темы в данном случае)
    private int getAppTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        codeStyle = sharedPreferences.getInt(APP_THEME, codeStyle);
        if (codeStyle == My_CALCULATOR_STYLE_DARK) {
            return R.style.MyCalculatorStyle_Dark;
        } else {
            return R.style.MyCalculatorStyle;
        }
    }

    private void makeDarkTheme() {
        settingsActivityLayout.setBackground(ContextCompat.getDrawable(this,
                R.drawable.dark_background));
        settingsName.setBackgroundColor(ContextCompat.getColor(this, R.color.purple));
        settingsName.setTextColor(ContextCompat.getColor(this, R.color.black));
        themeSwitch.setBackgroundColor(ContextCompat.getColor(this, R.color.forest_green));
    }

    private void makeLightTheme() {
        settingsActivityLayout.setBackground(ContextCompat.getDrawable(this,
                R.drawable.wood_texture));
        settingsName.setBackgroundColor(ContextCompat.getColor(this, R.color.cornflower_blue));
        settingsName.setTextColor(ContextCompat.getColor(this, R.color.floral_white));
        themeSwitch.setBackgroundColor(ContextCompat.getColor(this, R.color.light_green));
    }
}