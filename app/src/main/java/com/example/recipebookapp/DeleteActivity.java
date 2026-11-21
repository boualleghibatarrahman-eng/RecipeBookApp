package com.example.recipebookapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;

public class DeleteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        EditText deleteTitleTxt = findViewById(R.id.deleteTitleTxt);
        Button confirmDeleteBtn = findViewById(R.id.confirmDeleteBtn);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        confirmDeleteBtn.setOnClickListener(v -> {
            String title = deleteTitleTxt.getText().toString().trim();
            if (title.isEmpty()) {
                Snackbar.make(v, "Please enter a title", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.deleteRecipe(title)) {
                Snackbar.make(v, R.string.msg_deleted, Snackbar.LENGTH_SHORT).show();
                deleteTitleTxt.setText("");
            } else {
                Snackbar.make(v, R.string.msg_not_found, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}