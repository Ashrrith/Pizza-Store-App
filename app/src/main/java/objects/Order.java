package objects;

import java.util.ArrayList;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 */
public class Order implements Customizable {


    private ArrayList<Pizza> pizzas;
    private int orderNum;

    private static int ORDER_NUM = 0;


    /**
     * default constructor that sets unique order number and initializes pizzas arraylist
     */
    public Order()
    {
        pizzas = new ArrayList<>();
        ORDER_NUM += 1;
        orderNum = ORDER_NUM;
    }

    /**
     *
     * @param obj Pizza being added
     * @return true if added or false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(!(obj instanceof Pizza) || pizzas.contains(obj))
            return false;

        pizzas.add((Pizza) obj);

        return true;
    }

    /**
     * removes the pizza from the order
     * @param obj pizza being removed
     * @return
     */
    @Override
    public boolean remove(Object obj) {
        if(!(obj instanceof Pizza))
            return false;

        pizzas.remove((Pizza) obj);
        return true;
    }

    /**
     * removes pizza from order given an index
     * @param index index of the pizza being removed
     */
    public void removeIndex(int index){
        pizzas.remove(index);
    }

    /**
     *
     * @return unique order number
     */
    public int OrderNumber()
    {
        return orderNum;
    }

    /**
     * returns the pizzas arraylist
     */
    public ArrayList<Pizza> getPizzas()
    {
        return pizzas;
    }

    /**
     *
     * @return the size of order
     */
    public int getSize(){
        return pizzas.size();
    }

    /**
     *
     * @return string representation of the order
     */
    @Override
    public String toString(){
        String str = "";
        for(Pizza p : pizzas){
            str += p.toString() + "\n";
        }

        return str;
    }

}
