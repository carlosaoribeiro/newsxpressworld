package com.carlosribeiro.newsxpressworld;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> newsList = new ArrayList<>();
    private final OnNewsClickListener listener;


    public interface OnNewsClickListener {
        void onNewsClick(News news);
    }


    public NewsAdapter(OnNewsClickListener listener) {
        this.listener = listener;
    }


    public void submitList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.bind(news);
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView newsImageView;


        NewsViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            newsImageView = itemView.findViewById(R.id.newsImageView);


            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onNewsClick(newsList.get(position));
                }
            });
        }


        void bind(News news) {
            titleTextView.setText(news.getTitle());
            descriptionTextView.setText(news.getDescription());


            if (news.getImage() != null && !news.getImage().isEmpty()) {
                Log.d("IMAGE_URL", "URL da imagem: " + news.getImage());


                Glide.with(itemView.getContext())
                        .load(news.getImage())
                        .placeholder(R.drawable.error_image)  // Imagem temporária enquanto carrega
                        .error(R.drawable.error_image)  // Imagem de erro se falhar
                        .into(newsImageView);
            } else {
                Log.e("IMAGE_ERROR", "URL da imagem inválida!");
                newsImageView.setImageResource(R.drawable.error_image);
            }
        }




    }
}

