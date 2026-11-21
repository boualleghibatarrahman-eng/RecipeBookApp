package com.example.recipebookapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = findViewById(R.id.addBtn);
        Button searchBtn = findViewById(R.id.searchBtn);
        Button viewAllBtn = findViewById(R.id.viewAllBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);

        addBtn.setOnClickListener(v -> startActivity(new Intent(this, AddRecipeActivity.class)));
        searchBtn.setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        viewAllBtn.setOnClickListener(v -> startActivity(new Intent(this, ViewAllActivity.class)));
        deleteBtn.setOnClickListener(v -> startActivity(new Intent(this, DeleteActivity.class)));
    }
}