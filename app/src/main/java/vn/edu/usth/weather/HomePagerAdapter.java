package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomePagerAdapter extends FragmentStateAdapter {
    private final String[] cities;

    public HomePagerAdapter(@NonNull FragmentActivity activity, String[] cities) {
        super(activity);
        this.cities = cities;
    }

    // Called when ViewPager2 needs
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return WeatherAndForecastFragment.newInstance(cities[position]);
    }

    // Total number of pages
    @Override
    public int getItemCount() {
        return cities.length;
    }
}
