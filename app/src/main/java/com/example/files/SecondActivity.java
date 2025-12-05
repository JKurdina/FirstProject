package com.example.files;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class SecondActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Book> books = null;
    private ArrayList<Planet> planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String dataType = getIntent().getStringExtra("data_type");

        if("books".equals(dataType)) {
            books = FileManager.loadBooks(this);
            adapter = new BookAdapter(books, this);
            setTitle("Список книг");
        }
        else if ("planets".equals(dataType)) {
            planets = FileManager.loadPlanets(this);
            adapter = new PlanetAdapter(planets);
            setTitle("Список планет");
        }
        recyclerView.setAdapter(adapter);
    }


}
