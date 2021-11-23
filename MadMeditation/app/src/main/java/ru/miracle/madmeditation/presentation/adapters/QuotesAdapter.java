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
import ru.miracle.madmeditation.data.utils.Logger;
import ru.miracle.madmeditation.databinding.ItemQuoteBinding;
import ru.miracle.madmeditation.domain.entities.QuotesDto;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder>{

    List<QuotesDto> quotes;
    Context context;

    public QuotesAdapter(List<QuotesDto> quotes, Context context) {
        this.context = context;
        this.quotes = quotes;
    }

    @NonNull
    @NotNull
    @Override
    public QuotesAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new QuotesAdapter.ViewHolder(ItemQuoteBinding.inflate(LayoutInflater.from(context)));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull QuotesAdapter.ViewHolder holder, int position) {
        holder.itemQuoteBinding.quoteTitle.setText(quotes.get(position).getTitle());
        holder.itemQuoteBinding.quoteDescription.setText(quotes.get(position).getDescription());

        Picasso.get() // если фрагмент getActivity()
                .load(quotes.get(position).getImage()) // ссылка на изображение
                .placeholder(R.drawable.calm)
                .error(R.drawable.calm) // если не удалось загрузить картинку, ставить картинку по стандарту
                .into(holder.itemQuoteBinding.quoteImage);
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemQuoteBinding itemQuoteBinding;

        public ViewHolder(@NonNull @NotNull ItemQuoteBinding itemQuoteBinding) {
            super(itemQuoteBinding.getRoot());
            this.itemQuoteBinding = itemQuoteBinding;
        }
    }
}
