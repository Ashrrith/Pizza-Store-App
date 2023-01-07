package com.example.project5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import objects.BBQChicken;
import objects.ChicagoPizza;
import objects.Crust;
import objects.Deluxe;
import objects.Meatzza;
import objects.NYPizza;
import objects.Pizza;
import objects.PizzaFactory;
import objects.Size;
import objects.Topping;

/**
 * @author Visshnusaiashrrith Rachakunta,, Bhuvan Vinodh
 * Displays the page where a user can order a pizza
 */
public class OrderPageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ImageView orderingImageView;
    private TextView crustNameTextView;
    private TextView flavorTextView;
    private ListView availableToppingsListView;
    private ListView selectedToppingsListView;
    private TextView avaiListViewTitle;
    private TextView selectedListViewTitle;
    private TextView priceTextView;

    private String crustName;
    private int image;
    private String style;
    private String flavor;
    private ArrayList<String> availToppings;
    private ArrayList<String> selectedToppings;
    private ArrayAdapter<String> arrayAdapterForAvailableListView;
    private ArrayAdapter<String> arrayAdapterForSelectedListView;
    private Spinner sizeSpinner;
    private ArrayAdapter<CharSequence> sizeAdapter;
    private Size currSize;
    private PizzaFactory NYPizzaFactory;
    private PizzaFactory ChicPizzaFactory;

    private static final float SIZE_OF_SELECTED_TOPPINGS_TITLE = 16;
    private static final DecimalFormat df = new DecimalFormat("0.00");



    /**
     * Initializes all attributes and adds listeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        setImageAndCrustAndTitle();
        mysetTitle();
        populateAvailableToppings();
        availableToppingsListViewListener();
        launchHomeListener();
        setListviewTitles();
        populateSelectedToppings();
        selectedToppingsListViewListener();
        initializeSpinner();
        currSize = Size.SMALL;
        NYPizzaFactory = new NYPizza();
        ChicPizzaFactory = new ChicagoPizza();
        setPrice();
        addToOrderListener();
    }

    /**
     * sets the images and crust and flavor textviews
     */
    public void setImageAndCrustAndTitle(){
        orderingImageView = findViewById(R.id.orderingImageView);
        crustNameTextView = findViewById(R.id.crustNameTextView);
        flavorTextView = findViewById(R.id.flavorTextView);
        getData();
        setData();
    }

    /**
     * gets the data from myadapter class
     */
    private void getData(){
        if(getIntent().hasExtra("image") &&
        getIntent().hasExtra("data")
                && getIntent().hasExtra("style")
                && getIntent().hasExtra("flavor")){
            crustName = Crust.valueOf(getIntent().getStringExtra("data").toUpperCase())
                    .getStringCrustName();
            image = getIntent().getIntExtra("image", 1);

            String tempStyle = getIntent().getStringExtra("style");
            if(tempStyle.equalsIgnoreCase("New")){
                style = "New York";
            }
            else{
                style = "Chicago";
            }

            flavor = getIntent().getStringExtra("flavor");
        }
        else{
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * sets the text for crust and flavor
     */
    private void setData(){
        crustNameTextView.setText(crustName);
        orderingImageView.setImageResource(image);
        flavorTextView.setText(flavor);
    }

    /**
     * sets the title according to the style
     */
    private void mysetTitle(){
        if(style.equalsIgnoreCase("New York")){
            setTitle(R.string.ny_ordering_page_title);
        }
        else{
            setTitle(R.string.chic_ordering_page_title);
        }
    }

    /**
     * sets the listview titles based on flavor
     */
    private void setListviewTitles(){
        avaiListViewTitle = findViewById(R.id.avaiListViewTitle);
        selectedListViewTitle = findViewById(R.id.selectedListViewTitle);
        if(flavor.equalsIgnoreCase("Build your own")){
            avaiListViewTitle.setText(getString(R.string.avail_toppings_title_BYO));
            selectedListViewTitle.setText(getString(R.string.selected_toppings_title_BYO));
        }
        else{
            selectedListViewTitle.setText(getString(R.string.selected_toppings_title));
            selectedListViewTitle.setTextSize(SIZE_OF_SELECTED_TOPPINGS_TITLE);
        }
    }

    /**
     * launches home
     */
    public void launchHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * home button listener
     */
    public void launchHomeListener(){
        Button returnToHomeBtn = findViewById(R.id.returnToHomeBtn);
        returnToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHome();
            }
        });
    }

    /**
     * populates the available toppings for the listview
     */
    private void populateAvailableToppings(){
        availableToppingsListView = findViewById(R.id.availableToppingsListView);

        if(!(flavor.equalsIgnoreCase("Build Your Own"))){
            availableToppingsListView.setEnabled(false);
            return;
        }

        availToppings = (ArrayList<String>)Topping.listOfAllToppings().clone();
        availableToppingsListView.setEnabled(true);
        arrayAdapterForAvailableListView =
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1
                        , availToppings);

        availableToppingsListView.setAdapter(arrayAdapterForAvailableListView);
    }

    private void sevenToppingsToast(){
        Toast.makeText(this, "Can not add more than 7 toppings", Toast.LENGTH_SHORT).show();
    }

    /**
     * adds the topping selected to the selected toppings listview
     */
    private void availableToppingsListViewListener(){
        availableToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                if(selectedToppings.size() >= 7){
                    sevenToppingsToast();
                    return;
                }

                final int positionToRemove = position;
                AlertDialog.Builder alertdialog =
                        new AlertDialog.Builder(OrderPageActivity.this);
                alertdialog.setTitle("Add Topping");
                alertdialog.setMessage("Are you sure you want to add "
                        + availToppings.get(position) + "?");

                alertdialog.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String addedTopping = availToppings.get(positionToRemove);
                        availToppings.remove(positionToRemove);
                        arrayAdapterForAvailableListView.notifyDataSetChanged();
                        selectedToppings.add(addedTopping);
                        arrayAdapterForSelectedListView.notifyDataSetChanged();
                        setPrice();
                    }});

                alertdialog.setNegativeButton("Cancel", null);

                alertdialog.show();
            }
        });
    }

    /**
     * populates the selected toppings listview
     */
    private void populateSelectedToppings(){
        selectedToppingsListView = findViewById(R.id.selectedToppingsListView);
        Pizza pizza;

        if(flavor.equalsIgnoreCase("Deluxe Flavor")){
            pizza = new Deluxe();
        }
        else if(flavor.equalsIgnoreCase("Meatzza Flavor")){
            pizza = new Meatzza();
        }
        else {
            pizza = new BBQChicken();
        }

        if(flavor.equalsIgnoreCase("Build Your Own")){
            selectedToppings = new ArrayList<>();
        }
        else{
            selectedToppings = (ArrayList<String>)pizza.getToppings().clone();
        }

        arrayAdapterForSelectedListView =
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1
                        , selectedToppings);

        selectedToppingsListView.setAdapter(arrayAdapterForSelectedListView);
    }

    /**
     * toast to remind the user that non build your pizzas can not have their toppings removed
     */
    private void removingSelectedToppingsForNonBYO(){
        Toast.makeText(this, "Can not remove preset toppings", Toast.LENGTH_SHORT).show();
    }

    /**
     * selected toppings listener that removes a topping and adds it back to the avaliable toppings listview
     */
    private void selectedToppingsListViewListener(){
        selectedToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                if(!flavor.equalsIgnoreCase("Build Your Own")){
                    removingSelectedToppingsForNonBYO();
                    return;
                }

                final int positionToRemove = position;
                AlertDialog.Builder alertdialog =
                        new AlertDialog.Builder(OrderPageActivity.this);
                alertdialog.setTitle("Remove Topping");
                alertdialog.setMessage("Are you sure you want to remove "
                        + selectedToppings.get(position) + "?");

                alertdialog.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String removedTopping = selectedToppings.get(positionToRemove);
                        selectedToppings.remove(positionToRemove);
                        arrayAdapterForSelectedListView.notifyDataSetChanged();
                        availToppings.add(removedTopping);
                        arrayAdapterForAvailableListView.notifyDataSetChanged();
                        setPrice();
                    }});

                alertdialog.setNegativeButton("Cancel", null);

                alertdialog.show();
            }
        });
    }

    /**
     * initializes the size spinner
     */
    private void initializeSpinner(){
        sizeSpinner = findViewById(R.id.sizeSpinner);
        sizeAdapter = ArrayAdapter.createFromResource(this, R.array.sizes, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);
        sizeSpinner.setOnItemSelectedListener(this);
    }

    /**
     * if a size is selected it updates the price and notifies the user of the change
     * @param adapterView
     * @param view the size spinner
     * @param i the index of the item selected
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text + " size is selected", Toast.LENGTH_SHORT).show();
        currSize = Size.valueOf(text.toUpperCase());
        setPrice();
    }

    /**
     * does nothing no size is selected
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * removes all spaces in the toppings strings
     * @param toppings arraylist of toppings in String form
     * @return
     */
    public ArrayList<Topping> translateIntoToppingsArrayList(ArrayList<String> toppings){
        ArrayList<Topping> ts = new ArrayList<>();
        for(String s: toppings){
            ts.add(Topping.valueOf(s.toUpperCase().replaceAll("\\s", "")));
        }

        return ts;
    }


    /**
     * sets all the prices of all the textview including sales tax, subtotal, and total
     */
    public void setPrice(){
        Pizza pizza = null;

        if(flavor.equalsIgnoreCase("Build your own")){
            pizza = NYPizzaFactory.createBuildYourOwn();
            pizza.setToppings(translateIntoToppingsArrayList(selectedToppings));
        }
        else if(flavor.equalsIgnoreCase("Deluxe Flavor")){
            pizza = NYPizzaFactory.createDeluxe();
        }
        else if(flavor.equalsIgnoreCase("Meatzza Flavor")){
            pizza = NYPizzaFactory.createMeatzza();
        }
        else {
            pizza = NYPizzaFactory.createBBQChicken();
        }

        pizza.setSize(currSize);
        priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(df.format(pizza.price()));

    }

    /**
     *
     * @return the valid pizza factory based on style
     */
    public PizzaFactory getPizzaFactory(){
        if(style.equalsIgnoreCase("New York")){
            return NYPizzaFactory;
        }

        return ChicPizzaFactory;
    }

    /**
     * toast to indicate if a order has been placed
     */
    public void toastForAddingOrder(){
        Toast.makeText(this, "Order has been placed!", Toast.LENGTH_SHORT).show();
    }

    /**
     * adds the pizza to current order
     */
    public void addToOrderListener(){
        Button addToOrderBtn = findViewById(R.id.addToOrderBtn);
        addToOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create pizza with respective factory
                Pizza pizza = null;
                PizzaFactory pizzaFactory = getPizzaFactory();

                if(flavor.equalsIgnoreCase("Build your own")) {
                    pizza = pizzaFactory.createBuildYourOwn();
                    pizza.setToppings(translateIntoToppingsArrayList(selectedToppings));
                }
                else if(flavor.equalsIgnoreCase("BBQ Chicken Flavor")) {
                    pizza = pizzaFactory.createBBQChicken();
                }
                else if(flavor.equalsIgnoreCase("Meatzza Flavor")) {
                    pizza = pizzaFactory.createMeatzza();
                }
                else if(flavor.equalsIgnoreCase("Deluxe Flavor")) {
                    pizza = pizzaFactory.createDeluxe();
                }

                if(pizza == null)
                    return;

                pizza.setSize(currSize);
                PizzaStore.getCurrOrder().add(pizza);
                toastForAddingOrder();
            }
        });
    }
}