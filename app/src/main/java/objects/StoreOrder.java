package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * describes a store order which is represented by an Order class arraylist
 */
public class StoreOrder implements Customizable{

    ArrayList<Order> orders;

    /**
     * default constructor that initializes the orders arraylist
     */
    public StoreOrder()
    {
        orders = new ArrayList<>();
    }


    /**
     * adds an order to the orders arraylist
     * @param obj the order being added
     * @return true if added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(!(obj instanceof Order))
            return false;

        orders.add((Order) obj);

        return true;

    }

    /**
     * removes the order from the orders arraylist
     * @param obj the order being removed
     * @return true if removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        orders.remove((Order)obj);
        return true;
    }

    /**
     *
     * @return returns the orders arraylist
     */
    public ArrayList<Order> getOrders(){
        return orders;
    }

    /**
     * finds the order given a unique order number
     * @param orderNum the unique order number
     * @return an arraylist of pizzas with the order number
     */
    public ArrayList<Pizza> findOrder(int orderNum){
        for(Order o : orders){
            if(o.OrderNumber() == orderNum)
                return o.getPizzas();
        }

        return null;
    }

    /**
     * removes the order from the orders arraylist given an order number
     * @param orderNum the order number
     */
    public void removeIndex(int orderNum){
        for(int i = 0; i < orders.size(); i++)
        {
            if(orders.get(i).OrderNumber() == orderNum){
                remove(orders.get(i));
            }
        }
    }

    /**
     * toString
     * @return string representation of the store order object
     */
    @Override
    public String toString(){
        String str = "";
        for(Order o : orders)
        {
            str += o.toString() + "\n";
        }
        return str;
    }

    /**
     * exports the store order object to a file named storeOrders.txt
     */
    public void export(){
        try{
            String pathName = "src/storeOrders.txt";
            File file = new File(pathName);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(toString());
            bw.close();
        }
        catch(Exception ignored){

        }
    }
}
