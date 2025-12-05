package com.example.files;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_BOOKS = "saved_books.ser";
    private static final String FILE_PLANETS = "saved_planets.ser";

    public static void saveBooks(Context context, ArrayList<Book> books) {
        try (ObjectOutputStream output = new ObjectOutputStream(
                context.openFileOutput(FILE_BOOKS, Context.MODE_PRIVATE))) {

            output.writeObject(books);

            File file_path = new File(context.getFilesDir(), FILE_BOOKS);
            System.out.println("Книги сохранены в файл: " + file_path);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения книг в файл");
        }
    }

    public static void savePlanets(Context context, ArrayList<Planet> planets) {
        try (ObjectOutputStream output = new ObjectOutputStream(
                context.openFileOutput(FILE_PLANETS, Context.MODE_PRIVATE))) {

            output.writeObject(planets);

            File file_path = new File(context.getFilesDir(), FILE_PLANETS);
            System.out.println("Планеты сохранены в файл: " + file_path);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения планет в файл");
        }
    }

    public static ArrayList<Book> loadBooks(Context context) {
        try (ObjectInputStream input = new ObjectInputStream(
                context.openFileInput(FILE_BOOKS))) {

            @SuppressWarnings("unchecked")
            ArrayList<Book> books = (ArrayList<Book>) input.readObject();
            return books;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка чтения файла с книгами");
            return new ArrayList<Book>();
        }
    }

    public static ArrayList<Planet> loadPlanets(Context context) {
        try (ObjectInputStream input = new ObjectInputStream(
                context.openFileInput(FILE_PLANETS))) {

            @SuppressWarnings("unchecked")
            ArrayList<Planet> planets = (ArrayList<Planet>) input.readObject();
            return planets;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка чтения файла с планетами");
            return new ArrayList<Planet>();
        }
    }
}
