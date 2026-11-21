package com.example.recipebookapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;

public class AddRecipeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText titleTxt = findViewById(R.id.titleTxt);
        EditText stepsTxt = findViewById(R.id.stepsTxt);
        EditText keywordsTxt = findViewById(R.id.keywordsTxt);
        Button saveBtn = findViewById(R.id.saveBtn);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        saveBtn.setOnClickListener(v -> {
            String title = titleTxt.getText().toString().trim();
            String steps = stepsTxt.getText().toString().trim();
            String keywords = keywordsTxt.getText().toString().trim();

            if (title.isEmpty() || steps.isEmpty()) {
                Snackbar.make(v, R.string.msg_empty_fields, Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.checkTitleExists(title)) {
                Snackbar.make(v, R.string.msg_title_exists, Snackbar.LENGTH_LONG).show();
                return;
            }

            if (dbHelper.insertRecipe(title, steps, keywords)) {
                Snackbar.make(v, R.string.msg_saved, Snackbar.LENGTH_SHORT).show();
                finish();
            } else {
                Snackbar.make(v, "Error saving recipe", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}