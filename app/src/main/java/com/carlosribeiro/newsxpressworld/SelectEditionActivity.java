package com.carlosribeiro.newsxpressworld;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class SelectEditionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_edition);


        Button usEditionButton = findViewById(R.id.usEditionButton);
        Button internationalEditionButton = findViewById(R.id.internationalEditionButton);
        Button nextButton = findViewById(R.id.nextButton);


        usEditionButton.setOnClickListener(v -> {
            // Salvar escolha do usuário (exemplo: SharedPreferences)
            usEditionButton.setBackground(getResources().getDrawable(R.drawable.button_selector));
        });


        internationalEditionButton.setOnClickListener(v -> {
            // Salvar escolha do usuário
            internationalEditionButton.setBackground(getResources().getDrawable(R.drawable.button_selector));
        });


        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(SelectEditionActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

