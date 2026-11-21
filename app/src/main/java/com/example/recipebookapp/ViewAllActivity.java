package com.example.recipebookapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        ListView recipesListView = findViewById(R.id.recipesListView);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        ArrayList<String> recipesList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecipes();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
                String steps = cursor.getString(cursor.getColumnIndexOrThrow("STEPS"));
                String keywords = cursor.getString(cursor.getColumnIndexOrThrow("KEYWORDS"));

                String displayStr = "🍽️ " + title + "\nSteps: " + steps + "\nTags: " + keywords;
                recipesList.add(displayStr);
            } while (cursor.moveToNext());
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                recipesList
        );
        recipesListView.setAdapter(adapter);
    }
}