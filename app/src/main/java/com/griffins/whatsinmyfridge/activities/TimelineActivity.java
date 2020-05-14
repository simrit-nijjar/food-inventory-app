package com.griffins.whatsinmyfridge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.griffins.whatsinmyfridge.R;
import com.griffins.whatsinmyfridge.models.entities.Food;
import com.griffins.whatsinmyfridge.models.entities.Global;
import com.griffins.whatsinmyfridge.views.adapters.MyAdapter;

public class TimelineActivity extends AppCompatActivity {


    RecyclerView inventoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_timeline);

        createRV();
        setRVHeight();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        moveToMainActivity();
                        break;
                    case R.id.nav_timeline:
                        break;
                }
                return false;
            }
        });

    }



    private  void moveToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    private void createRV() {
        // Recycler View Not Expired (START)
        inventoryRecyclerView = findViewById(R.id.inventoryRecyclerView);

        final MyAdapter myAdapter = new MyAdapter(this, Global.notExpired);
        inventoryRecyclerView.setAdapter(myAdapter);
        inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
