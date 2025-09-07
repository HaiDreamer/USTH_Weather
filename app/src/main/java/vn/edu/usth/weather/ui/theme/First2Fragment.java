package vn.edu.usth.weather.ui.theme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import vn.edu.usth.weather.R;
import vn.edu.usth.weather.databinding.FragmentFirst2Binding;

public class First2Fragment extends Fragment {

    private FragmentFirst2Binding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirst2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}