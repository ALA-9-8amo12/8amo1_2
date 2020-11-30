package com.example.amazigh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent( MainActivity.this, SplashActivity.class);
                startActivity(homeIntent);
                finish();
            }
        //Button Spelen
        Button button = findViewById(R.id.buttonspelen);

        button.setOnClickListener(v -> {

            Intent intent = new Intent(this, CategorieActivity.class);

            startActivity(intent);
        });
        //Button Oefenen gaat naar Categorie ook, want daarna in de andere activity wordt er een button naar het daadwerkelijke Oefenen scherm gedaan.
        //Allebei deze buttons moeten uiteraard naar categorie gaan.
        Button button1 = findViewById(R.id.buttonoefenen);

        button1.setOnClickListener(v -> {

            Intent intent = new Intent(this, CategorieActivity.class);

            startActivity(intent);
        });

        //Button Score
        Button button2 = findViewById(R.id.buttonscore);

        button2.setOnClickListener(v -> {

            Intent intent = new Intent(this, ScoreActivity.class);

            startActivity(intent);
        });

        //Button Over
        Button button3 = findViewById(R.id.buttonover);

        button3.setOnClickListener(v -> {

            Intent intent = new Intent(this, OverActivity.class);

            startActivity(intent);
        }, SPLASH_TIME_OUT);
    }


}

