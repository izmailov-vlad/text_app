package ru.miracle.madmeditation.presentation.view.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import retrofit2.Response;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.presentation.view.dashboard.DashboardActivity;
import ru.miracle.madmeditation.presentation.view.start.StartActivity;
import ru.miracle.madmeditation.presentation.viewmodel.DashboardViewModel;
import ru.miracle.madmeditation.presentation.viewmodel.LoginViewModel;

public class SplashActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getUser();

        loginViewModel.userInfoMutableLiveData.observe(this, userResponse -> {
            if(!userResponse.isSuccessful()) {
                Logger.error(new Throwable("server error"));
            }
            if(userResponse.body() != null) {
                DashboardActivity.open(this);
            }
            else {
                StartActivity.open(this);
            }
            finish();
        });

    }
}