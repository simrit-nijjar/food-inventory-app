package com.griffins.whatsinmyfridge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.griffins.whatsinmyfridge.R;
import com.griffins.whatsinmyfridge.models.entities.Food;

import java.io.Serializable;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ImageView mainImageView;
    ImageButton editBtn;
    TextView foodName, expValue, catValue, locValue, amntValue, notesValue;

    String data1, data2, data3, data4, data5, data6;
    Food item;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mainImageView = findViewById(R.id.mainImageView);
        editBtn = findViewById(R.id.editBtn);
        foodName = findViewById(R.id.foodName);
        expValue = findViewById(R.id.expValue);
        catValue = findViewById(R.id.catValue);
        locValue = findViewById(R.id.locValue);
        amntValue = findViewById(R.id.amntValue);
        notesValue = findViewById(R.id.notesValue);

        getData();
        setData();

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToEditSecondActivity();
            }
        });
    }

    private void moveToEditSecondActivity() {
        Intent intent = new Intent(this, com.griffins.whatsinmyfridge.activities.EditSecondActivity.class);
        startActivity(intent);
    }

    private void getData() {
        if(getIntent().hasExtra("item")) {

            item = (Food) getIntent().getSerializableExtra("item");

            data1 = item.getName();
            data2 = item.getExpiration();
            data3 = item.getLocation();
            data4 = item.getAmount();
            data5 = item.getCategory();
            data6 = item.getNotes();
            myImage = item.getImage();

        }

        else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        expValue.setText(data2);
        catValue.setText(data5);
        locValue.setText(data3);
        amntValue.setText(data4);
        foodName.setText(data1);
        notesValue.setText(data6);
        mainImageView.setImageResource(myImage);
    }

}
