package ru.miracle.madmeditation.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ru.miracle.madmeditation.R;
import ru.miracle.madmeditation.databinding.ItemFeelingBinding;
import ru.miracle.madmeditation.domain.entities.FeelingsDto;

public class FeelingAdapter extends RecyclerView.Adapter<FeelingAdapter.ViewHolder> {

    List<FeelingsDto> feelings;
    Context context;

    public FeelingAdapter(List<FeelingsDto> feelings, Context context) {
        this.feelings = feelings;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public FeelingAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new FeelingAdapter.ViewHolder(ItemFeelingBinding.inflate(LayoutInflater.from(context)));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeelingAdapter.ViewHolder holder, int position) {
        holder.itemFeelingBinding.feelingTitle.setText(feelings.get(position).getTitle());

        Picasso.get() // если фрагмент getActivity()
                .load(feelings.get(position).getImage()) // ссылка на изображение
                .placeholder(R.drawable.ic_feeling_item)
                .error(R.drawable.ic_feeling_item) // если не удалось загрузить картинку, ставить картинку по стандарту
                .into(holder.itemFeelingBinding.feelingImage);

    }

    @Override
    public int getItemCount() {
        return feelings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemFeelingBinding itemFeelingBinding;

        public ViewHolder(@NonNull @NotNull ItemFeelingBinding itemFeelingBinding) {
            super(itemFeelingBinding.getRoot());
            this.itemFeelingBinding = itemFeelingBinding;
        }
    }
}
