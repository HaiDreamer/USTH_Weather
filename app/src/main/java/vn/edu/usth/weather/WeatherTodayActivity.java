package vn.edu.usth.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WeatherTodayActivity extends AppCompatActivity {

    private final String[] titles = new String[]{"Hanoi", "Paris", "Toulouse"};
    //change img to usth logo link as expected, but i am lazy to change it
    private static final String USTH_LOGO_URL = "https://usth.edu.vn/wp-content/uploads/2021/11/search.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_today);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ViewPager2 pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(new HomePagerAdapter(this, titles));

        TabLayout tabs = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabs, pager, (tab, position) -> {
            tab.setText(titles[position]);
        }).attach();

        //background load(from internet) img in activity
        Glide.with(this)
                .load(USTH_LOGO_URL)
                .into(new com.bumptech.glide.request.target.CustomTarget<android.graphics.drawable.Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull android.graphics.drawable.Drawable resource,
                                                com.bumptech.glide.request.transition.Transition<? super android.graphics.drawable.Drawable> transition) {
                        // make it a bit transparent so content stays readable
                        resource.setAlpha(140); // 0-255
                        pager.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(android.graphics.drawable.Drawable placeholder) {
                        pager.setBackground(placeholder);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
