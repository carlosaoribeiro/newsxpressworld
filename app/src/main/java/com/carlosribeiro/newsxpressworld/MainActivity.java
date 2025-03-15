package com.carlosribeiro.newsxpressworld;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private NewsViewModel newsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_main);


        // Atualiza a data atual
        TextView currentDate = findViewById(R.id.currentDate);
        String todayDate = new SimpleDateFormat("MMMM dd", new Locale("pt", "BR")).format(new Date());
        currentDate.setText(todayDate);


        // Configuração do RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this::openNewsDetails);
        recyclerView.setAdapter(newsAdapter);


        // Configuração do ViewModel
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.getNews().observe(this, newsList -> newsAdapter.submitList(newsList));


        // Botão para abrir a tela de assinatura
        Button subscriptionButton = findViewById(R.id.subscriptionButton);
        subscriptionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SubscriptionActivity.class);
            startActivity(intent);
        });
    }


    private void openNewsDetails(News news) {
        Intent intent = new Intent(this, NewsDetailsActivity.class);
        intent.putExtra("news_title", news.getTitle());
        intent.putExtra("news_description", news.getDescription());
        intent.putExtra("news_url", news.getUrl());
        if (news.getImage() != null && !news.getImage().isEmpty()) {
            intent.putExtra("news_image", news.getImage());
        } else {
            intent.putExtra("news_image", "");
        }
        startActivity(intent);
    }
}

