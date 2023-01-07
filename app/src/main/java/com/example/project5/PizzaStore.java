package com.example.project5;

import objects.Order;
import objects.StoreOrder;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Maintains the current order and store orders
 */
public class PizzaStore {
    private static Order currOrder;
    private static StoreOrder orders;

    /**
     * default constructor to set currOrder order obj and orders storeOrder obj
     */
    public PizzaStore(){
        currOrder = new Order();
        orders = new StoreOrder();
    }

    /**
     *
     * @return the current Order
     */
    public static Order getCurrOrder(){
        return currOrder;
    }

    /**
     *
     * @return the store orders
     */
    public static StoreOrder getStoreOrders(){
        return orders;
    }

    /**
     * creates a new order
     */
    public static void resetCurrOrder(){
        currOrder = new Order();
    }


}
