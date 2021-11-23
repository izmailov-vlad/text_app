package ru.miracle.madmeditation.presentation.view.photo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import retrofit2.Response;
import ru.miracle.madmeditation.R;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.databinding.ActivityPhotoBinding;
import ru.miracle.madmeditation.domain.entities.PhotoDto;
import ru.miracle.madmeditation.presentation.view.dashboard.DashboardActivity;
import ru.miracle.madmeditation.presentation.view.photo.gesture.GestureListener;
import ru.miracle.madmeditation.presentation.viewmodel.DashboardViewModel;
import ru.miracle.madmeditation.presentation.viewmodel.LoginViewModel;
import ru.miracle.madmeditation.presentation.viewmodel.PhotoViewModel;

public class PhotoActivity extends AppCompatActivity {

    ActivityPhotoBinding binding;
    GestureDetector gestureDetector;
    private PhotoViewModel photoViewModel;
    private Photo photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        photoViewModel = new ViewModelProvider(this).get(PhotoViewModel.class);

        photo = (Photo) getIntent().getExtras().getSerializable(Photo.class.getSimpleName());

        Picasso.get() // если фрагмент getActivity()
                .load(photo.getImage()) // ссылка на изображение
                .placeholder(R.drawable.add_photo_btn)
                .error(R.drawable.add_photo_btn) // если не удалось загрузить картинку, ставить картинку по стандарту
                .into(binding.photo);


        gestureDetector = new GestureDetector(binding.photo.getContext(), new GestureListener(binding.photo));

        binding.photo.setOnTouchListener((view, motionEvent) -> gestureDetector.onTouchEvent(motionEvent));

        binding.deletePhotoTxt.setOnClickListener(view -> {
            photoViewModel.deletePhotoByUid(photo.getUid());

        });

        photoViewModel.deletePhotoMutableLiveData.observe(this, booleanResponse -> {
            Log.e("TAG", "delete success");
            finish();
        });

        binding.closeBtn.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    static public void open(Context context, Photo photo) {
        context.startActivity(new Intent(context, PhotoActivity.class).putExtra(Photo.class.getSimpleName(), photo));
    }
}