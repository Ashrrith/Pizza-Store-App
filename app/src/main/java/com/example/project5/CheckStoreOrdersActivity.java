package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import objects.Pizza;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Displays the page with details of the store orders
 */
public class CheckStoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner orderSpinner;
    private ArrayAdapter<Integer> orderAdapter;
    private int currOrderNum;
    private Integer[] orderNumbersArr;
    private ListView orderListView;
    private ArrayList<String> currOrderPizzas;
    private ArrayAdapter<String> arrayAdapterForCurrOrderPizzas;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final double TAX_RATE = 0.0625;

    /**
     * Initializes all attributes and adds listeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_store_orders);
        setTitle(R.string.store_order_title);
        initializeSpinner();
        launchHomeListener();
        cancelOrderListener();
    }

    /**
     * creates an array with all the current active order numbers
     */
    private void createOrderNumbersArr(){
        int size = PizzaStore.getStoreOrders().getOrders().size();
        orderNumbersArr = new Integer[size];
        for(int i = 0; i<size; i++){
            orderNumbersArr[i] = PizzaStore.getStoreOrders().getOrders().get(i).OrderNumber();
        }
    }

    /**
     * initializes the spinner and adds the listeners
     */
    private void initializeSpinner(){
        createOrderNumbersArr();
        orderSpinner = findViewById(R.id.orderSpinner);
        orderAdapter = new ArrayAdapter<Integer>(CheckStoreOrdersActivity.this, android.R.layout.simple_spinner_item, orderNumbersArr);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(orderAdapter);
        orderSpinner.setOnItemSelectedListener(this);
        if(orderNumbersArr.length == 0 && currOrderPizzas != null){
            currOrderPizzas.removeAll(currOrderPizzas);
            arrayAdapterForCurrOrderPizzas.notifyDataSetChanged();
            TextView totalOrder = findViewById(R.id.orderTotal);
            totalOrder.setText(df.format(0));
        }
    }

    /**
     * if a item is selected in the spinner it populates the listview with that order's pizzas
     * @param adapterView
     * @param view the spinner
     * @param i the index of the selected item
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        currOrderNum = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        populateOrderListView();

    }

    /**
     * adds all the pizzas in string form to an arraylist related to the listview
     * @param pizzas current order number's Arraylist of Pizza objects
     */
    private void translatePizzaArrToStringArr(ArrayList<Pizza> pizzas){
        for(int i = 0; i < pizzas.size(); i++){
            currOrderPizzas.add(pizzas.get(i).toString());
        }
    }

    /**
     * populates the listview with the current order's pizzas
     */
    private void populateOrderListView(){
        orderListView = findViewById(R.id.orderListView);
        setPrice();

        currOrderPizzas = new ArrayList<>();
        translatePizzaArrToStringArr(PizzaStore.getStoreOrders().findOrder(currOrderNum));
        arrayAdapterForCurrOrderPizzas =
                new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1
                        , currOrderPizzas);

        orderListView.setAdapter(arrayAdapterForCurrOrderPizzas);
    }

    /**
     * does nothing if nothing is selected in the spinner
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /**
     * launches home page
     */
    public void launchHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * home page button listener
     */
    public void launchHomeListener(){
        Button homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHome();
            }
        });
    }

    /**
     * cancels the order and re-initializes the spinner
     */
    public void cancelOrderListener(){
        Button cancelOrderBtn = findViewById(R.id.cancelOrderBtn);
        cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PizzaStore.getStoreOrders().removeIndex(currOrderNum);
                initializeSpinner();
            }
        });
    }

    /**
     * sets the price for the current order chosen
     */
    public void setPrice(){
        ArrayList<Pizza> currOrder = PizzaStore.getStoreOrders().findOrder(currOrderNum);
        double price = 0;

        for(Pizza p : currOrder){
            price += p.price();
        }

        double salesTax = TAX_RATE*price;
        price += salesTax;

        TextView totalOrder = findViewById(R.id.orderTotal);
        totalOrder.setText(df.format(price));
    }
}