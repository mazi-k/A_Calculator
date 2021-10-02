package com.example.a_calculator;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.NightTheme);
        else
            setTheme(R.style.DayTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initialViews();
        setupViews();
    }

    private void initialViews() {
        radioGroup = findViewById(R.id.themes_radio_group);
    }

    private void setupViews() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case -1:
                    Toast.makeText(SettingsActivity.this, "Change nothing!", Toast.LENGTH_LONG).show();
                    break;
                case R.id.radio_button_theme_auto:
                    Toast.makeText(this, "Auto", Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    break;
                case R.id.radio_button_theme_day:
                    Toast.makeText(this, "Day", Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case R.id.radio_button_theme_night:
                    Toast.makeText(this, "Night", Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                default:
                    break;
            }

        });
    }

}