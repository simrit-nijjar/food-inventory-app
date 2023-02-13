package com.griffins.whatsinmyfridge.activities;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.griffins.whatsinmyfridge.R;
import com.griffins.whatsinmyfridge.models.entities.Food;
import com.griffins.whatsinmyfridge.models.entities.Global;
import com.griffins.whatsinmyfridge.views.adapters.MyAdapter;

public class MainActivity<milk> extends AppCompatActivity {

    private FloatingActionButton AddFood;
    RecyclerView inventoryRecyclerView;
    private Button allBtn, fridgeBtn, pantryBtn;
    private MenuItem invBtn, timelineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);

        allBtn = findViewById(R.id.allSubMenuBtn);
        fridgeBtn = findViewById(R.id.fridgeSubMenuBtn);
        pantryBtn = findViewById(R.id.pantrySubMenuBtn);
        AddFood = findViewById(R.id.addFoodBtn);


        createRV();
        setRVHeight();

        for (int i = 0; i < Global.notExpired.size(); i++) {
            Global.notExpired.get(i).description();
        }

        // Button Actions (START)

        // Add food button
        AddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAddFoodActivity();
            }
        });

        // fridge button
        fridgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // pantry button
        pantryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // all button
        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToTimelineActivity();
            }
        });

        // Button Actions (END)

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_timeline:
                        moveToTimelineActivity();
                        break;
                }
                return false;
            }
        });

    }


    private void moveToAddFoodActivity() {
        Intent intent = new Intent(MainActivity.this, AddEditFoodActivity.class);
        startActivity(intent);
    }

    private  void moveToTimelineActivity() {
        Intent intent = new Intent(MainActivity.this, TimelineActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    private void createRV() {
        // Recycler View Not Expired (START)
        inventoryRecyclerView = findViewById(R.id.inventoryRecyclerView);

    /*

        Global.notExpired.add(0, new Food("Milk", "06/05/2021", "Fridge", "4", "Beverage", "", R.drawable.cat_dairy, R.drawable.ic_fridge));
        Global.notExpired.add(1, new Food("Orange Juice", "06/11/2021", "Fridge", "1", "Beverage", "", R.drawable.cat_beverages, R.drawable.ic_fridge));
        Global.notExpired.add(2, new Food("Nesquick", "06/25/2021", "pantry", "1", "Cereal", "", R.drawable.cat_cereal, R.drawable.ic_pantry));
        Global.notExpired.add(3, new Food("Chocolate Milk", "06/17/2021", "Fridge", "2", "Beverage", "", R.drawable.cat_dairy, R.drawable.ic_fridge));
        Global.notExpired.add(4, new Food("Green Pepper", "06/01/2021", "Fridge", "11", "Vegetable", "",  R.drawable.cat_vegetables, R.drawable.ic_fridge));
        Global.notExpired.add(5, new Food("Tomato", "06/14/2021", "Fridge", "7", "Vegetable", "", R.drawable.cat_vegetables, R.drawable.ic_fridge));


     */

        final MyAdapter myAdapter = new MyAdapter(this, Global.notExpired);
        inventoryRecyclerView.setAdapter(myAdapter);
        inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Global.notExpired.remove(viewHolder.getAdapterPosition());
                inventoryRecyclerView.removeViewAt(viewHolder.getAdapterPosition());

                myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                myAdapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(), Global.notExpired.size());


                myAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();

                // Refresh Page
                Intent refresh =  new Intent(MainActivity.this, MainActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(refresh);
                overridePendingTransition(0, 0);

            }
        }).attachToRecyclerView(inventoryRecyclerView);
        // Recycler View Not Expired (END)
    }


    private void setRVHeight() {
        inventoryRecyclerView.setNestedScrollingEnabled(false);
        ViewGroup.LayoutParams layoutParams = inventoryRecyclerView.getLayoutParams();
        layoutParams.height = Global.notExpired.size() * 171;
        // System.out.println("Not Expired list: " + Global.notExpired.size());
        inventoryRecyclerView.setLayoutParams(layoutParams);
    }


}
