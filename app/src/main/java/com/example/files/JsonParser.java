package com.example.files;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class JsonParser {
    public static ArrayList<Book> getBooksFromRaw(Context context) throws IOException {
        try (InputStream inputStream = context.getResources().openRawResource(R.raw.file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
            ArrayList<Book> bookList = gson.fromJson(reader, listType);

            return bookList != null ? bookList : new ArrayList<Book>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public static ArrayList<Planet> getPlanetsFromRaw(Context context) throws IOException {
        try (InputStream inputStream = context.getResources().openRawResource(R.raw.planets);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Planet>>(){}.getType();
            ArrayList<Planet> planetList = gson.fromJson(reader, listType);

            return planetList != null ? planetList : new ArrayList<Planet>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
