package com.carlosribeiro.newsxpressworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class NewsDetailsActivity extends AppCompatActivity {
    private TextView titleTextView, descriptionTextView;
    private ImageView newsImageView;
    private Button openBrowserButton, shareButton;
    private String newsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        // Obtendo referências dos elementos da UI
        newsImageView = findViewById(R.id.newsImageView);
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        openBrowserButton = findViewById(R.id.btnNavegador);
        shareButton = findViewById(R.id.btnCompartilhar);

        // Obtendo dados da Intent
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("news_title");
            String description = intent.getStringExtra("news_description");
            String imageUrl = intent.getStringExtra("news_image");
            newsUrl = intent.getStringExtra("news_url");

            Log.d("DETAILS_DATA", "Recebido: " + title + " | URL: " + imageUrl);

            // Atualizando UI com os dados recebidos
            titleTextView.setText(title);
            descriptionTextView.setText(description);

            // Carregando imagem com Glide
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(this)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_foreground) // Enquanto carrega
                        .error(R.drawable.placeholder_foreground) // Caso falhe
                        .into(newsImageView);
            } else {
                Log.e("IMAGE_ERROR", "URL da imagem inválida!");
                newsImageView.setImageResource(R.drawable.placeholder_foreground);
            }
        }

        // Configuração do botão "Abrir no Navegador"
        openBrowserButton.setOnClickListener(v -> {
            if (newsUrl != null && !newsUrl.isEmpty()) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl));
                startActivity(browserIntent);
            } else {
                Toast.makeText(NewsDetailsActivity.this, "URL não disponível", Toast.LENGTH_SHORT).show();
            }
        });

        // Configuração do botão "Compartilhar"
        shareButton.setOnClickListener(v -> {
            if (newsUrl != null && !newsUrl.isEmpty()) {
                String shareText = titleTextView.getText().toString() + "\nLeia mais: " + newsUrl;

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

                startActivity(Intent.createChooser(shareIntent, "Compartilhar notícia via"));
            } else {
                Toast.makeText(NewsDetailsActivity.this, "Não há link para compartilhar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
