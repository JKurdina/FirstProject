package com.example.files;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Book> books = null;
    private ArrayList<Planet> planets = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            books = JsonParser.getBooksFromRaw(this);
            FileManager.saveBooks(this, books);
        } catch (IOException e) {
            Toast.makeText(this, "Не удалось загрузить список книг", Toast.LENGTH_LONG).show();
        }

        try {
            planets = JsonParser.getPlanetsFromRaw(this);
            FileManager.savePlanets(this, planets);
        } catch (IOException e) {
            Toast.makeText(this, "Не удалось загрузить список планет", Toast.LENGTH_LONG).show();
        }


        Button btnOpenSecond = findViewById(R.id.btnOpenBooks);
        btnOpenSecond.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("data_type", "books");
            startActivity(intent);
//            Можно также через Intent отправить serializable объекты
        });

        Button btnOpenPlanets = findViewById(R.id.btnOpenPlanet);
        btnOpenPlanets.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("data_type", "planets");
            startActivity(intent);
        });

        Button btnLoadImage = findViewById(R.id.btnLoadImage);
        btnLoadImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
        });
    }
}
