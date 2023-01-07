package objects;

import java.util.ArrayList;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * describes a pizza
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;


    /**
     * default constructor
     */
    public Pizza()
    {
        toppings = new ArrayList<>();
        crust = null;
        size = null;
    }

    /**
     *
     * @param crust pizza crust
     * @param size size of the pizza
     */
    public Pizza(Crust crust, Size size){
        toppings = new ArrayList<>();
        this.crust = crust;
        this.size = size;
    }

    public abstract double price();

    public abstract String pizzaFlavor();

    /**
     * sets the crust of a given pizza
     * @param c pizza crust
     */
    public void setCrust(Crust c)
    {
        crust = c;
    }

    /**
     * sets the size of a given pizza
     * @param s size of the pizza
     */
    public void setSize(Size s)
    {
        size = s;
    }

    /**
     * sets Toppings given an Arraylist of toppings
     * @param t toppings arraylist
     */
    public void setToppings(ArrayList<Topping> t)
    {
        toppings = t;
    }

    /**
     *
     * @return the crust
     */
    public Crust getCrust(){
        return crust;
    }

    /**
     *
     * @return the size of the pizza
     */
    public Size getSize()
    {
        return size;
    }

    /**
     *
     * @return the toppings string form of a given pizza
     */
    public String getToppingsStr(){
        String str = "";
        for(int i = 0; i<this.getToppings().size(); i++)
        {
            str += this.getToppings().get(i).getStringName() + ", ";
        }

        return str;
    }

    /**
     *
     * @return the topping arraylist of a given pizza
     */
    public ArrayList<Topping> getToppings()
    {
        return toppings;
    }
}