package vn.edu.usth.weather;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//link image on internet: messenger

public class WeatherActivity extends AppCompatActivity {
    private final String TAG = "WeatherActivity";
    //main thread handler
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    //single background thread
    private final ExecutorService settingExecutor = Executors.newSingleThreadExecutor();

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mediaPlayer = MediaPlayer.create(this, R.raw.farout);

        Button weather_today = findViewById(R.id.weather_today);
        weather_today.setOnClickListener(v -> {
            //background thread
            new Thread(() -> {
                try {
                    // simulate 1s delay from internet
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                mainHandler.post(() -> {
                    Intent intent = new Intent(this, WeatherTodayActivity.class);
                    startActivity(intent);
                });
            }).start();
            Log.d("ThreadCheck", "Running on: " + Thread.currentThread().getName());
        });

//        Button setting_weather = findViewById(R.id.setting);
//        setting_weather.setOnClickListener(v -> runFakeNetworkTask());

        Button settingBtn = findViewById(R.id.setting);
        settingBtn.setOnClickListener(v -> runFakeNetworkThen(() -> {
            Toast.makeText(this, "Response: Fake network response", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(WeatherActivity.this, SettingActivity.class));
        }));
    }

//    private void runFakeNetworkTask() {
//        final WeakReference<WeatherActivity> activityRef = new WeakReference<>(this);
//
//        settingExecutor.submit(() -> {
//            String result;
//            try {
//                Thread.sleep(1500); // simulate network delay
//                result = "Fake network response";
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                result = "Interrupted";
//            }
//
//            // switch to main/UI thread
//            String finalResult = result;
//            mainHandler.post(() -> {
//                WeatherActivity activity = activityRef.get();
//                if (activity == null || activity.isFinishing()) return;
//                Toast.makeText(activity, "Response: " + finalResult, Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(this, SettingActivity.class);
//                activity.startActivity(intent);
//            });
//        });
//    }


    private void runFakeNetworkThen(Runnable uiAction) {
        WeakReference<WeatherActivity> ref = new WeakReference<>(this);

        settingExecutor.submit(() -> {
            String result;
            try {
                Thread.sleep(1000); // simulate network delay
                result = "OK";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                result = "Interrupted";
            }

            // Post back to main thread
            String finalResult = result;
            mainHandler.post(() -> {
                WeatherActivity activity = ref.get();
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) return;

                Log.d(TAG, "Network result: " + finalResult);
                if (uiAction != null) uiAction.run(); // safe: runs on UI thread
            });
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
        settingExecutor.shutdownNow();
        Log.i(TAG, "onDestroy");
        releasePlayer();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer = MediaPlayer.create(this, R.raw.farout);
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