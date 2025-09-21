package vn.edu.usth.weather;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.media.MediaPlayer;

public class WeatherActivity extends AppCompatActivity {
    private final String TAG = "WeatherActivity";

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mediaPlayer = MediaPlayer.create(this, R.raw.farout);

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

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        releasePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        releasePlayer();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    private void releasePlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
            } catch (IllegalStateException ignored) {}
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}