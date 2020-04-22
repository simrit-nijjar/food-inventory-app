package com.griffins.whatsinmyfridge.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.griffins.whatsinmyfridge.R;
import com.griffins.whatsinmyfridge.models.entities.Food;
import com.griffins.whatsinmyfridge.models.entities.Global;

import java.util.ArrayList;
import java.util.Calendar;

public class AddFoodActivity extends AppCompatActivity {

    private EditText nameET, catET, expET, amntET, locET, notesET;
    private Button addFoodBtn;
    private ArrayList<Food> notExpired = Global.notExpired;
    private Spinner catSpinner, locSpinner;

    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        nameET = findViewById(R.id.nameEditText);
        expET = findViewById(R.id.expirationEditText);
        amntET = findViewById(R.id.amountEditText);
        notesET = findViewById(R.id.notesEditText);
        locSpinner = findViewById(R.id.locationSpinner);
        catSpinner = findViewById(R.id.categorySpinner);

        addFoodBtn = findViewById(R.id.addFoodBtn);

        expET.setInputType(InputType.TYPE_NULL);
        expET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int dayOfMonth = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(AddFoodActivity.this,
                        new DatePickerDialog.OnDateSetListener() {@Override public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { expET.setText((month + 1) + "/" + dayOfMonth + "/" + year); }
                        }
                        , year, month, dayOfMonth);

                picker.show();

            }
        });


        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

        // Create Spinner Adapters
        ArrayAdapter<CharSequence> catAdapter = ArrayAdapter.createFromResource(this, R.array.category_options, android.R.layout.simple_spinner_item);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(catAdapter);

        ArrayAdapter<CharSequence> locAdapter = ArrayAdapter.createFromResource(this, R.array.location_options, android.R.layout.simple_spinner_item);
        locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locSpinner.setAdapter(locAdapter);

    }

    private void readData() {

        // get all entered data
        String s1 = nameET.getText().toString();
        String s2 = expET.getText().toString();
        String s3 = locSpinner.getSelectedItem().toString();
        String s4 = amntET.getText().toString();
        String s5 = catSpinner.getSelectedItem().toString();
        String s6 = notesET.getText().toString();
        int image;
        int image2;

        // determine food icon
        switch (s5.toLowerCase()) {
            case "beverage":
                image = R.drawable.cat_beverages;
                break;
            case "bread":
                image = R.drawable.cat_bread;
                break;
            case "cereal":
                image = R.drawable.cat_cereal;
                break;
            case "dairy":
                image = R.drawable.cat_dairy;
                break;
            case "fruit":
                image = R.drawable.cat_fruit;
                break;
            case "legumes":
                image = R.drawable.cat_legumes;
                break;
            case "meat":
                image = R.drawable.cat_meat;
                break;
            case "vegetables":
                image = R.drawable.cat_vegetables;
            default:
                image = R.drawable.cat_food;
                break;
        }

        // determine location icon
        switch (s3.toLowerCase()) {
            case "fridge":
                image2 = R.drawable.ic_fridge;
                break;
            case "pantry":
                image2 = R.drawable.ic_pantry;
                break;
            default:
                image2 = R.drawable.ic_location;
        }

        // add new item to ArrayList of Food items
        if (s1.equals(" ") || s2.equals("")) {
            Toast.makeText(this, "Please enter both Food Name and Best Before date", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("values are: " + "'" + s1 + "' and '" + s2 + "'");

            Global.notExpired.add(new Food(s1, s2, s3, s4, s5, s6, image, image2));

            // delete all entered data from user screen
            nameET.setText("");
            expET.setText("");
            amntET.setText("");
            notesET.setText("");

            // move back to MainActivity
            moveToMainActivity();

            // inform user of "Item Added!"
            Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
        }




    }

    private void moveToMainActivity() {
        Intent intent = new Intent(AddFoodActivity.this, com.griffins.whatsinmyfridge.activities.MainActivity.class);
        startActivity(intent);
    }

}