package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeatherAndForecastFragment extends Fragment {

    private static final String ARG_CITY = "city";

    public static WeatherAndForecastFragment newInstance(String city) {
        WeatherAndForecastFragment f = new WeatherAndForecastFragment();
        Bundle b = new Bundle();
        b.putString(ARG_CITY, city);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String city = getArguments() != null ? getArguments().getString(ARG_CITY) : null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);
    }
}