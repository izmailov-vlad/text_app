package ru.miracle.madmeditation.presentation.view.start;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.miracle.madmeditation.R;
import ru.miracle.madmeditation.databinding.FragmentRegisterBinding;
import ru.miracle.madmeditation.presentation.view.dashboard.DashboardActivity;
import ru.miracle.madmeditation.presentation.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    private RegisterViewModel registerViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container,false);
        registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);

        registerViewModel.registerLiveData.observe(getViewLifecycleOwner(), response -> {
            if(response) {
                DashboardActivity.open(requireContext());
                requireActivity().finish();
            }
        });

        binding.registerBtn.setOnClickListener(view -> {
            String login = binding.emailEdt.getText().toString();
            String password = binding.passwordEdt.getText().toString();
            if(!login.equals("") && !password.equals("") && login.contains("@"))
                registerViewModel.register(login, password);
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    static void open(View view) {
        Navigation.findNavController(view).navigate(R.id.navigateToRegisterFragment);
    }
}