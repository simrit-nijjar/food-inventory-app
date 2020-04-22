package com.griffins.whatsinmyfridge.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.griffins.whatsinmyfridge.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddFoodActivity extends AppCompatActivity {
    DatePickerDialog picker;
    EditText eText;
    TextView tvw;

    private static final String TAG = "AddFoodPage";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_page);
        tvw=(TextView)findViewById(R.id.textView1);
        eText=(EditText)findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int dayOfMonth = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(AddFoodActivity.this,
                        new DatePickerDialog.OnDateSetListener() {@Override public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { eText.setText(dayOfMonth + "/" + (month + 1) + "/" + year); }
                        }
                        , year, month, dayOfMonth);

                picker.show();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        Spinner dropdown = findViewById(R.id.category);
        String[] categories = new String[]{"Category", "Fruits & Vegetables", "Meat & Seafood", "Dairy", "Frozen", "Drinks", "Baked Goods"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        dropdown.setAdapter(adapter);



    }
}




