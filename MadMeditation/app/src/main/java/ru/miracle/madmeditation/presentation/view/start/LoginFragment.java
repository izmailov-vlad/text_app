package ru.miracle.madmeditation.presentation.view.start;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import retrofit2.Response;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.presentation.view.dashboard.DashboardActivity;
import ru.miracle.madmeditation.R;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.databinding.FragmentLoginBinding;
import ru.miracle.madmeditation.presentation.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        loginViewModel.userInfoMutableLiveData.observe(getViewLifecycleOwner(), userResponse -> {
            if(userResponse.isSuccessful() && userResponse.body() != null) {
                binding.emailEdt.setText(userResponse.body().login);
            }
        });

        loginViewModel.loginResponseLiveData.observe(getViewLifecycleOwner(), logInResponse -> {
            Logger.log("loginResponseLiveData observer", logInResponse.toString());
            if(logInResponse) {
                DashboardActivity.open(requireContext());
                requireActivity().finish();
            }
            else {
                Logger.log("Authorization", "error");
            }
        });

        binding.registerBtn.setOnClickListener(view -> {
            RegisterFragment.open(binding.getRoot());
        });

        binding.loginBtn.setOnClickListener(view -> {

            String login = binding.emailEdt.getText().toString();
            String password = binding.passwordEdt.getText().toString();


            loginViewModel.login(login, password);

        });

        return binding.getRoot();
    }
    

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        loginViewModel.getUser();
    }

    static void open(View view) {
        Navigation.findNavController(view).navigate(R.id.navigateToLoginFragment);
    }
}