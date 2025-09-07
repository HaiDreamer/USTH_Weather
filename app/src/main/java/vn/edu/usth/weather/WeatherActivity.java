package vn.edu.usth.weather;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import vn.edu.usth.weather.databinding.ActivityWeatherBinding;
import vn.edu.usth.weather.ui.theme.ForecastFragment;

public class WeatherActivity extends AppCompatActivity {
    private String TAG = "WeatherActivity";     //help identify which class come form

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i(TAG, "onCreate");

        Button weather_today = findViewById(R.id.weather_today);
        weather_today.setOnClickListener(v -> {
            Intent intent = new Intent(WeatherActivity.this, WeatherTodayActivity.class);
            startActivity(intent);
        });

        Button setting = findViewById(R.id.setting);
        setting.setOnClickListener(v -> {
            Intent intent = new Intent(WeatherActivity.this, SettingActivity.class);
            startActivity(intent);
        });

        if (savedInstanceState == null) { // prevents re-adding fragment on screen rotation
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new ForecastFragment())
                    .commit();
        }
    }

}