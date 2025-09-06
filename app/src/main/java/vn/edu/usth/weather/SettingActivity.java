package vn.edu.usth.weather;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import vn.edu.usth.weather.databinding.ActivitySetting2Binding;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the title from the Activity label in Manifest
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTitle());
        }
        // Back arrow click → go to main screen
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        });

    }
}