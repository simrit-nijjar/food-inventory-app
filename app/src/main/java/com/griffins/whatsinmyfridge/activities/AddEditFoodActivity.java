package com.griffins.whatsinmyfridge.activities;

import android.annotation.SuppressLint;
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

public class AddEditFoodActivity extends AppCompatActivity {

    private EditText nameET, catET, expET, amntET, locET, notesET;
    private Button addFoodBtn;
    private Spinner catSpinner, locSpinner;

    private DatePickerDialog picker;

    private Food item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addeditfood);

        nameET = findViewById(R.id.nameEditText);
        expET = findViewById(R.id.expirationEditText);
        amntET = findViewById(R.id.amountEditText);
        notesET = findViewById(R.id.notesEditText);
        locSpinner = findViewById(R.id.locationSpinner);
        catSpinner = findViewById(R.id.categorySpinner);

        addFoodBtn = findViewById(R.id.addFoodBtn);

        // Create Calendar Selector For Date
        expET.setInputType(InputType.TYPE_NULL);
        expET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int dayOfMonth = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(AddEditFoodActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { expET.setText((month + 1) + "/" + dayOfMonth + "/" + year); }
                        }
                        , year, month, dayOfMonth);

                picker.show();

            }
        });

        // Create Spinner Adapters
        ArrayAdapter<CharSequence> catAdapter = ArrayAdapter.createFromResource(this, R.array.category_options, android.R.layout.simple_spinner_item);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(catAdapter);

        ArrayAdapter<CharSequence> locAdapter = ArrayAdapter.createFromResource(this, R.array.location_options, android.R.layout.simple_spinner_item);
        locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locSpinner.setAdapter(locAdapter);

        // Update text on all fields if Editing existing Food Item
        if(getIntent().hasExtra("item")) {

            int locPos, catPos;

            setTitle("Edit");
            addFoodBtn.setText("Done");

            item = (Food) getIntent().getSerializableExtra("item");

            switch (item.getLocation().toLowerCase()) {
                case "fridge":
                    locPos = 0;
                    break;
                case "pantry":
                    locPos = 1;
                    break;
                default:
                    locPos = 0;
                    Toast.makeText(this, "Location Error", Toast.LENGTH_SHORT).show();
                    break;
            }

            switch (item.getCategory().toLowerCase()) {
                case "beverage":
                    catPos = 0;
                    break;
                case "bread":
                    catPos = 1;
                    break;
                case "cereal":
                    catPos = 2;
                    break;
                case "dairy":
                    catPos = 3;
                    break;
                case "fruit":
                    catPos = 4;
                    break;
                case "lugumes":
                    catPos = 5;
                    break;
                case "meat":
                    catPos = 6;
                    break;
                case "vegetables":
                    catPos = 7;
                    break;
                default:
                    catPos = 0;
                    Toast.makeText(this, "Category Error", Toast.LENGTH_SHORT).show();
                    break;
            }

            nameET.setText(item.getName());
            expET.setText(item.getExpiration());
            locSpinner.setSelection(locPos);
            amntET.setText(item.getAmount());
            catSpinner.setSelection(catPos);
            notesET.setText(item.getNotes());
        }

        // Execute code when clicking Add button
        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

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

        if(getIntent().hasExtra("item")) {
            setEditData(s1, s2, s3, s4, s5, s6, image, image2);
            // Toast.makeText(this, "Functionality Not Added Yet", Toast.LENGTH_SHORT).show();
        }
        else {
            // Add Data to ArrayList
            setAddData(s1, s2, s3, s4, s5, s6, image, image2);
        }
    }

    // Add new food item to ArrayList
    private void setAddData(String s1, String s2, String s3, String s4, String s5, String s6, int image, int image2) {
        // add new item to ArrayList of Food items
        if (s1.equals(" ") || s2.equals("")) {
            Toast.makeText(this, "Please enter both Food Name and Best Before date", Toast.LENGTH_SHORT).show();
        } else {
            // create new food item
            Global.notExpired.add(new Food(s1, s2, s3, s4, s5, s6, image, image2));

            // move back to MainActivity
            moveToMainActivity();

            // inform user of "Item Added!"
            Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
        }
    }

    // update edited food item to ArrayList
    private void setEditData(String s1, String s2, String s3, String s4, String s5, String s6, int image, int image2) {
        // add new item to ArrayList of Food items
        if (s1.equals(" ") || s2.equals("")) {
            Toast.makeText(this, "Please enter both Food Name and Best Before date", Toast.LENGTH_SHORT).show();
        } else {
            // create new food item
            int pos = getIntent().getIntExtra("pos", -1);
            Global.notExpired.get(pos).setFoodName(s1);
            Global.notExpired.get(pos).setExpiration(s2);
            Global.notExpired.get(pos).setLocation(s3);
            Global.notExpired.get(pos).setAmount(s4);
            Global.notExpired.get(pos).setCategory(s5);
            Global.notExpired.get(pos).setNotes(s6);
            Global.notExpired.get(pos).setImage(image);
            Global.notExpired.get(pos).setImage2(image2);

            // move back to MainActivity
            moveToMainActivity();

            // inform user of "Item Added!"
            Toast.makeText(this, "Item Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    // Move back to Main Activity
    private void moveToMainActivity() {
        Intent intent = new Intent(AddEditFoodActivity.this, com.griffins.whatsinmyfridge.activities.MainActivity.class);
        startActivity(intent);
    }

}