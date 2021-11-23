package ru.miracle.madmeditation.presentation.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.miracle.madmeditation.R;
import ru.miracle.madmeditation.data.models.photo.Photo;
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.databinding.ItemPhotoBinding;
import ru.miracle.madmeditation.domain.entities.PhotoDto;
import ru.miracle.madmeditation.presentation.adapters.actions.OnAddPhotoClicked;
import ru.miracle.madmeditation.presentation.view.photo.PhotoActivity;

public class PhotosAdapter extends BaseAdapter {

    private Context context;
    private List<Photo> photos;
    private LayoutInflater inflater;
    private View rootView;
    private OnAddPhotoClicked onAddPhotoClicked;

    public PhotosAdapter(OnAddPhotoClicked onAddPhotoClicked, Context context, List<Photo> photos, View rootView) {
        Log.e("TAG", "size: " + String.valueOf(photos.size()));
        this.context = context;
        this.photos = photos;
        this.rootView = rootView;
        this.onAddPhotoClicked = onAddPhotoClicked;
    }

    public void addPhoto(Photo photo) {
        photos.add(0, photo);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int i) {
        return photos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return photos.get(i).getUid();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        TextView textView;

        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        view = inflater.inflate(R.layout.item_photo, null);
        imageView = view.findViewById(R.id.photo_image);
        textView = view.findViewById(R.id.time_txt);

        Log.e("TAG", "index" + String.valueOf(i));
        Log.e("TAG", photos.get(i).getImage());


        Picasso.get() // если фрагмент getActivity()
                .load(photos.get(i).getImage())// ссылка на изображение
                .placeholder(R.drawable.add_photo_btn)
                .error(R.drawable.add_photo_btn) // если не удалось загрузить картинку, ставить картинку по стандарту
                .into(imageView);

        textView.setText(photos.get(i).getTime());

        if(photos.get(i).getTime().equals("")) {
            imageView.setOnClickListener(view1 -> onAddPhotoClicked.click());
        }
        else {
            imageView.setOnClickListener(action -> {
                PhotoActivity.open(context, photos.get(i));
            });
        }
        return view;
    }
}
