package com.griffins.whatsinmyfridge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.griffins.whatsinmyfridge.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton AddFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddFood = findViewById(R.id.addFood);

        AddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToAddFoodPage();

            }
        });

    }

    private void moveToAddFoodPage(){

        Intent intent = new Intent(MainActivity.this, AddFoodPage.class);
        startActivity(intent);
    }
}
