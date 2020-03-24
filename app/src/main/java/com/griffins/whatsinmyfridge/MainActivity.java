package com.griffins.whatsinmyfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
