package com.example.recipebookapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.google.android.material.snackbar.Snackbar;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchTxt = findViewById(R.id.searchTxt);
        Button searchBtn = findViewById(R.id.searchBtn);
        CardView resultCard = findViewById(R.id.resultCard);
        TextView resTitle = findViewById(R.id.resTitle);
        TextView resSteps = findViewById(R.id.resSteps);
        TextView resKeywords = findViewById(R.id.resKeywords);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        searchBtn.setOnClickListener(v -> {
            String query = searchTxt.getText().toString().trim();
            if (query.isEmpty()) return;

            Cursor cursor = dbHelper.searchRecipe(query);
            if (cursor != null && cursor.moveToFirst()) {
                resultCard.setVisibility(View.VISIBLE);
                resTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow("TITLE")));
                resSteps.setText("Steps: " + cursor.getString(cursor.getColumnIndexOrThrow("STEPS")));
                resKeywords.setText("Keywords: " + cursor.getString(cursor.getColumnIndexOrThrow("KEYWORDS")));
                cursor.close();
            } else {
                resultCard.setVisibility(View.GONE);
                Snackbar.make(v, R.string.msg_not_found, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}