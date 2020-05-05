package com.griffins.whatsinmyfridge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.griffins.whatsinmyfridge.R;
import com.griffins.whatsinmyfridge.models.entities.Global;
import com.griffins.whatsinmyfridge.views.adapters.MyAdapter;

public class MainActivity<milk> extends AppCompatActivity {

    private Button AddFood;
    RecyclerView inventoryRecyclerView;
    RecyclerView expInventoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRV();
        setRVHeight();

        for (int i = 0; i < Global.notExpired.size(); i++) {
            Global.notExpired.get(i).description();
        }

        // Button Actions (START)
        AddFood = findViewById(R.id.addFoodBtn);

        AddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToAddFoodActivity();
            }
        });
        // Button Actions (END)

    }


    private void moveToAddFoodActivity() {
        Intent intent = new Intent(MainActivity.this, AddEditFoodActivity.class);
        startActivity(intent);
    }

    private void createRV() {
        // Recycler View Not Expired (START)
        inventoryRecyclerView = findViewById(R.id.inventoryRecyclerView);

        //Global.notExpired.add(0, new Food("Milk", "05/05/2020", "Fridge", "4L", "Beverage", "", R.drawable.cat_dairy, R.drawable.ic_fridge));
        //Global.notExpired.add(1, new Food("Orange Juice", "05/11/2020", "Fridge", "4L", "Beverage", "", R.drawable.cat_beverages, R.drawable.ic_fridge));
        //Global.notExpired.add(2, new Food("Nesquick", "05/25/2020", "pantry", "4L", "Cereal", "", R.drawable.cat_cereal, R.drawable.ic_pantry));
        //Global.notExpired.add(3, new Food("Chocolate Milk", "05/17/2020", "Fridge", "4L", "Beverage", "", R.drawable.cat_dairy, R.drawable.ic_fridge));
        //Global.notExpired.add(4, new Food("Green Pepper", "05/01/2020", "Fridge", "4L", "Vegetable", "",  R.drawable.cat_vegetables, R.drawable.ic_fridge));
        //Global.notExpired.add(5, new Food("Tomato", "05/14/2020", "Fridge", "4L", "Vegetable", "", R.drawable.cat_vegetables, R.drawable.ic_fridge));


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
        layoutParams.height = Global.notExpired.size() * 275;
        // System.out.println("Not Expired list: " + Global.notExpired.size());
        inventoryRecyclerView.setLayoutParams(layoutParams);
    }


}
