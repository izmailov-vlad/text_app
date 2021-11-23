package ru.miracle.madmeditation.presentation.view.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import ru.miracle.madmeditation.R;
import ru.miracle.madmeditation.data.models.FeelingsResponse;
import ru.miracle.madmeditation.data.models.user.User;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.databinding.FragmentDashboardBinding;
import ru.miracle.madmeditation.presentation.adapters.FeelingAdapter;
import ru.miracle.madmeditation.presentation.adapters.QuotesAdapter;
import ru.miracle.madmeditation.presentation.viewmodel.DashboardViewModel;


public class DashboardFragment extends Fragment {
    FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;
    Dialog dialog;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        RecyclerView.LayoutManager feelingsLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager quotesLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);

        binding.feelingsRecyclerView.setLayoutManager(feelingsLayoutManager);
        binding.quotesRecyclerView.setLayoutManager(quotesLayoutManager);

        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        dashboardViewModel.getFeelings();
        dashboardViewModel.getQuotes();

        dashboardViewModel.getUserInfoLiveData().observe(getViewLifecycleOwner(), user -> {

            if(user.isSuccessful() && user.body() != null) {
                binding.welcomeTitle.setText("С возвращением, ".concat(user.body().login));
            }
            else dashboardViewModel.getUserInfo();


        });

        dashboardViewModel.getQuotesResponseLiveData().observe(getViewLifecycleOwner(), quotesResponse -> {
            if(!quotesResponse.isSuccessful()) {
                dialog = new Dialog(requireActivity());

                // Установите заголовок
                dialog.setTitle("Заголовок диалога");
                // Передайте ссылку на разметку
                dialog.setContentView(R.layout.dialog_server_error);
                // Найдите элемент TextView внутри вашей разметки
                // и установите ему соответствующий текст

                TextView text = (TextView) dialog.findViewById(R.id.error_text);
                text.setText(quotesResponse.message());
            }
            if(quotesResponse.body() != null) {
                binding.quotesRecyclerView.setAdapter(
                        new QuotesAdapter(quotesResponse.body().data, requireContext())
                );
            } else dashboardViewModel.getQuotes();

        });

        dashboardViewModel.getFeelingsResponseLiveData().observe(getViewLifecycleOwner(), feelingsResponse -> {
            if(feelingsResponse.body() != null && feelingsResponse.isSuccessful()) {
                binding.feelingsRecyclerView.setAdapter(
                        new FeelingAdapter(feelingsResponse.body().data, requireContext()
                        )
                );
            };

        });
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}