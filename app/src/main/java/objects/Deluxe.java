package objects;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Describes the Deluxe pizza
 */
public class Deluxe extends Pizza{
    private static final ArrayList<Topping> deluxeToppings = new ArrayList<>(
            Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREENPEPPER,
                    Topping.ONION, Topping.MUSHROOM)
    );

    /**
     * default constructor that sets all the toppings
     */
    public Deluxe(){
        super();
        this.setToppings(deluxeToppings);
    }


    /**
     * @param obj topping that is being tried to be added
     * @return false
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }

    /**
     * @param obj the topping being removed
     * @return false
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }

    /**
     *
     * @return the price of the Deluxe pizza based on size
     */
    @Override
    public double price() {
        if(this.getSize() == Size.SMALL)
        {
            if(this.getCrust().equals(Crust.DEEPDISH) ||
                    this.getCrust().equals(Crust.BROOKLYN))
            {
                return 14.99;
            }
        }
        else if(this.getSize() == Size.MEDIUM)
        {
            if(this.getCrust().equals(Crust.DEEPDISH) ||
                    this.getCrust().equals(Crust.BROOKLYN))
            {
                return 16.99;
            }
        }
        else {
            if(this.getCrust().equals(Crust.DEEPDISH) ||
                    this.getCrust().equals(Crust.BROOKLYN))
            {
                return 18.99;
            }
        }
        return 0;
    }

    /**
     *
     * @return returns the pizza flavor in string form
     */
    @Override
    public String pizzaFlavor() {
        return "Deluxe";
    }

    /**
     *
     * @return the toString method
     */
    @Override
    public String toString()
    {
        String style = "";
        if(this.getCrust().equals(Crust.DEEPDISH))
        {
            style = "(Chicago Style - Deep Dish)";
        }
        else{
            style = "(New York Style - Brooklyn)";
        }


        return "Deluxe" + style + ", " + getToppingsStr() + this.getSize().toString() + " $" + price();
    }

}
