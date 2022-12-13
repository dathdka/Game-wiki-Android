package com.example.gamewiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class searchActivity extends AppCompatActivity {
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Vật phẩm", "Vật phẩm có thể thế tạo", "Công thức chế tạo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        Toast.makeText((Context) this, (CharSequence) dropdown.getSelectedItem(), Toast.LENGTH_SHORT).show();
    }
}