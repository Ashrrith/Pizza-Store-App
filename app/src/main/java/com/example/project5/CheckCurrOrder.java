package com.example.project5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Displays a page a user can check the current order and the the totals for it
 */
public class CheckCurrOrder extends AppCompatActivity {

    private ListView orderListView;

    private ArrayList<String> pizzas;
    private ArrayAdapter arrayAdapterForOrderListView;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final double TAX_RATE = 0.0625;
    private static final int EMPTY = 0;

    /**
     * Initializes all attributes and adds listeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_curr_order);
        setTitle(R.string.check_curr_order_title);
        setOrderNumber();
        launchHomeListener();
        populateListView();
        setPrice();
        orderListViewListener();
        clearOrderListener();
        placeOrderListener();
    }

    /**
     * sets the order number for the textview
     */
    private void setOrderNumber(){
        TextView orderNumTextView = findViewById(R.id.orderNumTextView);
        orderNumTextView.setText(PizzaStore.getCurrOrder().OrderNumber() + "");
    }

    /**
     * launches the home page
     */
    public void launchHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * home button listener, which launches the home page
     */
    public void launchHomeListener(){
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHome();
            }
        });
    }

    /**
     * populates the listview with current order's pizzas
     */
    public void populateListView(){
        pizzas = new ArrayList<>();
        orderListView = findViewById(R.id.orderListView);

        for(int i = 0; i<PizzaStore.getCurrOrder().getSize(); i++){
            pizzas.add(PizzaStore.getCurrOrder().getPizzas().get(i).toString());
            System.out.println(pizzas.get(i));
        }

        arrayAdapterForOrderListView =
                new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1
                        , pizzas);

        orderListView.setAdapter(arrayAdapterForOrderListView);
    }

    /**
     * current order listview listener that removes pizzas if a pizza is selected
     */
    private void orderListViewListener(){
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                final int positionToRemove = position;
                AlertDialog.Builder alertdialog =
                        new AlertDialog.Builder(CheckCurrOrder.this);
                alertdialog.setTitle("Remove Pizza");
                alertdialog.setMessage("Are you sure you want to remove "
                        + pizzas.get(position) + "?");

                alertdialog.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        pizzas.remove(positionToRemove);
                        PizzaStore.getCurrOrder().removeIndex(positionToRemove);
                        arrayAdapterForOrderListView.notifyDataSetChanged();
                        setPrice();
                    }});

                alertdialog.setNegativeButton("Cancel", null);

                alertdialog.show();
            }
        });
    }

    /**
     * sets the prices for all the textviews including the subtotal, sales tax, and the total
     */
    public void setPrice(){
        double pizzaPrice = 0;

        for(int i = 0; i<PizzaStore.getCurrOrder().getSize(); i++) {
            pizzaPrice+=PizzaStore.getCurrOrder().getPizzas().get(i).price();
        }
        double st = pizzaPrice*TAX_RATE;
        double total = st + pizzaPrice;

        TextView subtotalTextView = findViewById(R.id.subtotalTextView);
        TextView salesTaxTextView = findViewById(R.id.salesTaxTextView);
        TextView orderTotalTextView = findViewById(R.id.orderTotalTextView);

        subtotalTextView.setText(df.format(pizzaPrice));
        salesTaxTextView.setText(df.format(st));
        orderTotalTextView.setText(df.format(total));
    }

    /**
     * clears the current order
     */
    private void clearOrder(){
        PizzaStore.getCurrOrder().getPizzas().removeAll(
                PizzaStore.getCurrOrder().getPizzas());

        pizzas.removeAll(pizzas);
        arrayAdapterForOrderListView.notifyDataSetChanged();
        setPrice();
    }

    /**
     * clear order button listener that clears the current order
     */
    public void clearOrderListener(){
        Button clearOrderBtn = findViewById(R.id.clearOrderBtn);
        clearOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearOrder();
            }
        });
    }

    /**
     * places the current order and stores it in the store order obj
     */
    private void placeOrder(){

        if(PizzaStore.getCurrOrder().getSize() == EMPTY && pizzas.size() == EMPTY)
        {
            return;
        }

        PizzaStore.getStoreOrders().add(PizzaStore.getCurrOrder());
        PizzaStore.resetCurrOrder();
        Toast.makeText(this, "Order has been placed!", Toast.LENGTH_SHORT).show();

        launchHome();
    }

    /**
     * place order button listener
     */
    public void placeOrderListener(){
        Button placeOrderBtn = findViewById(R.id.placeOrderBtn);
        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

}