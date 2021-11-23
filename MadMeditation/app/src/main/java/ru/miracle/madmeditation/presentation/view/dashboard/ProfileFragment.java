package ru.miracle.madmeditation.presentation.view.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.databinding.FragmentProfileBinding;
import ru.miracle.madmeditation.domain.entities.PhotoDto;
import ru.miracle.madmeditation.presentation.adapters.PhotosAdapter;
import ru.miracle.madmeditation.presentation.adapters.actions.OnAddPhotoClicked;
import ru.miracle.madmeditation.presentation.view.start.StartActivity;
import ru.miracle.madmeditation.presentation.viewmodel.DashboardViewModel;
import ru.miracle.madmeditation.presentation.viewmodel.PhotoViewModel;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private List<Photo> times = new ArrayList<>();
    private FragmentProfileBinding binding;
    private PhotosAdapter photosAdapter;
    private DashboardViewModel dashboardViewModel;
    private PhotoViewModel photoViewModel;

    private final OnAddPhotoClicked onAddPhotoClicked = () -> {
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    };

//    private final OnAddPhotoClicked onAddCatPhotoClicked = () -> {
//        dashboardViewModel.getCat();
//    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == 1 && data != null) {

            final Uri imageUri = data.getData();
            // Текущее время
            Date currentDate = new Date();

            // Форматирование времени как "день.месяц.год"
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);

            // Форматирование времени как "часы:минуты:секунды"
            DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String timeText = timeFormat.format(currentDate);
            dashboardViewModel.insertPhoto(new Photo(timeText, imageUri.toString()));

            Logger.log("picture pick", "success");
        }
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        Log.e("TAG", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("TAG", "onCreateView");

        dashboardViewModel.photosResponseMutableLiveData.observe(getViewLifecycleOwner(), listResponse -> {
            Log.e("TAG", "photosResponseMutableLiveData");

            if(listResponse.body() != null) {
                Logger.log("TAG", listResponse.body().toString());
                times = listResponse.body();
                times.add(new Photo( "", Uri.parse("android.resource://ru.miracle.madmeditation/drawable/add_photo_btn").toString()));
                photosAdapter = new PhotosAdapter(onAddPhotoClicked, requireActivity(), times, binding.getRoot());
                binding.photosGridView.setAdapter(photosAdapter);
            }

        });

        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        binding.navIcon.setOnClickListener(view -> {
            StartActivity.open(requireActivity());
            requireActivity().finish();
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "onResume");
        dashboardViewModel.getAllPhotos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}