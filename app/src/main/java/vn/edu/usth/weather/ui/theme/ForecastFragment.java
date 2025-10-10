package vn.edu.usth.weather.ui.theme;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import vn.edu.usth.weather.R;

public class ForecastFragment extends Fragment {

    private static final String USTH_LOGO_URL =
            "https://usth.edu.vn/wp-content/uploads/2021/11/search.png";

    private com.bumptech.glide.request.target.CustomTarget<Drawable> bgTarget;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // view is ScrollView root
        Glide.with(this)
                .load(USTH_LOGO_URL)
                .into(new com.bumptech.glide.request.target.CustomTarget<android.graphics.drawable.Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull android.graphics.drawable.Drawable resource,
                                                @Nullable com.bumptech.glide.request.transition.Transition<? super android.graphics.drawable.Drawable> transition) {
                        resource.setAlpha(140);
                        view.setBackground(resource); // set background on the root ScrollView
                    }
                    @Override
                    public void onLoadCleared(@Nullable android.graphics.drawable.Drawable placeholder) {
                        view.setBackground(placeholder);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bgTarget != null) {
            Glide.with(this).clear(bgTarget); // avoid leaks when view is destroyed
            bgTarget = null;
        }
    }

}


