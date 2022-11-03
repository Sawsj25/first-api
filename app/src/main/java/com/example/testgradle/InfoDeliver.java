package com.example.testgradle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoDeliver extends AppCompatActivity {
    private EditText searchEditText;
    private ImageView searchImageView;
    private ImageView contactImageView;
    private TextView nameTextView;
    private TextView numberTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_deliver);
        searchEditText= findViewById(R.id.searchEditText);
        searchImageView = findViewById(R.id.searchImageView);
        contactImageView = findViewById(R.id.contactImageView);
        nameTextView = findViewById(R.id.nameTextView);
        numberTextView = findViewById(R.id.numberTextView);
    }
}