package ru.miracle.madmeditation.presentation.view.start;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import ru.miracle.madmeditation.R;
import ru.miracle.madmeditation.databinding.FragmentOnboardBinding;

public class OnboardFragment extends Fragment {

    FragmentOnboardBinding binding;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardBinding.inflate(inflater, container, false);
        binding.enterButton.setOnClickListener(action -> LoginFragment.open(binding.getRoot()));
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}