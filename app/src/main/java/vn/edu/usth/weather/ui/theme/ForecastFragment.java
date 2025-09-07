package vn.edu.usth.weather.ui.theme;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ForecastFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Create an empty View (no layout XML needed)
        View view = new View(getContext());

        // Example: red with transparency (#20FF0000)
        view.setBackgroundColor(Color.parseColor("#20FF0000"));

        // You can later change to #2000FF00 (green) or #200000FF (blue)
        // just by editing this line.

        return view;
    }
}
