package com.example.files;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String dataType = getIntent().getStringExtra("data_type");

        if ("books".equals(dataType)) {
            ArrayList<Book> books = getIntent().getParcelableArrayListExtra("books_list");
            if (books != null && !books.isEmpty()) {
                adapter = new BookAdapter(books);
                setTitle("Список книг");
            }
        } else if ("planets".equals(dataType)){
            ArrayList<Planet> planets = getIntent().getParcelableArrayListExtra("planet_list");
            adapter = new PlanetAdapter(planets);
            setTitle("Список планет");
        }
        recyclerView.setAdapter(adapter);
    }
}
