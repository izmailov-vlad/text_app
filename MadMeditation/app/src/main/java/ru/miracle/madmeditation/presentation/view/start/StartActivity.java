package ru.miracle.madmeditation.presentation.view.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import ru.miracle.madmeditation.R;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.databinding.ActivityStartBinding;
import ru.miracle.madmeditation.databinding.FragmentLoginBinding;
import ru.miracle.madmeditation.presentation.view.photo.PhotoActivity;

public class StartActivity extends AppCompatActivity {
    private NavController navController;
    private ActivityStartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onBackPressed() {
        Logger.log("onBackPressed", "called");
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.popBackStack() || super.onSupportNavigateUp();
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, StartActivity.class));
    }
}