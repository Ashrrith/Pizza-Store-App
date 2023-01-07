package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * the main page where user can navigate to other pages
 */
public class MainActivity extends AppCompatActivity {

    public static boolean storeCreated;
    private String[] pizzaOptions;
    int[] images = {R.drawable.deep_dish_deluxe, R.drawable.pan_bbqchicken,
            R.drawable.stuffed_meatzza, R.drawable.pan_buildyourown,
            R.drawable.brooklyn_deluxe, R.drawable.thin_bbqchicken,
            R.drawable.handtossed_meatzza, R.drawable.handtossed_buildyourown};

    RecyclerView pizzaOptionsRecyclerView;

    /**
     * Initializes all attributes and adds listeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.main_menu)); //setting title
        createStore(); //initializing store
        initializeRecyclerView();
        launchCheckCurrOrderListener(); //curr order image button listener
        launchCheckStoreOrdersListener(); //store order image button listener
    }

    /**
     * creates a pizzastore obj
     */
    public void createStore(){
        if(!storeCreated){
            PizzaStore dummyPizzaStore = new PizzaStore();
            storeCreated = true;
        }
    }

    /**
     * initializes the recycler view with all pizza options
     */
    public void initializeRecyclerView(){
        pizzaOptionsRecyclerView = findViewById(R.id.pizzaOptionsRecyclerView);
        pizzaOptions = getResources().getStringArray(R.array.pizza_options);

        MyAdapter myAdapter = new MyAdapter(this, pizzaOptions, images);
        pizzaOptionsRecyclerView.setAdapter(myAdapter);
        pizzaOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * launches the check store orders page
     */
    public void launchCheckStoreOrders(){
        Intent intent = new Intent(this, CheckStoreOrdersActivity.class);
        startActivity(intent);
    }

    /**
     * store orders button listener
     */
    public void launchCheckStoreOrdersListener(){
        ImageButton storeOrdersBtn = findViewById(R.id.storeOrdersBtn);
        storeOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCheckStoreOrders();
            }
        });
    }

    /**
     * launches the current order page
     */
    public void launchCheckCurrOrder(){
        Intent intent = new Intent(this, CheckCurrOrder.class);
        startActivity(intent);
    }

    /**
     * current order button listener
     */
    public void launchCheckCurrOrderListener(){
        ImageButton currentOrderBtn = findViewById(R.id.currentOrderBtn);
        currentOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCheckCurrOrder();
            }
        });

    }




}