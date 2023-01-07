package objects;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Describes the Meatzza pizza
 */
public class Meatzza extends Pizza{
    private static final ArrayList<Topping> meatzzaTopping = new ArrayList<>(
            Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF,
                    Topping.HAM)
    );

    /**
     * default constructor that sets all the toppings
     */
    public Meatzza(){
        super();
        this.setToppings(meatzzaTopping);
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
     * @return the price of the BBQ chicken pizza based on size
     */
    @Override
    public double price() {
        if(this.getSize() == Size.SMALL)
        {
            if(this.getCrust().equals(Crust.STUFFED) ||
                    this.getCrust().equals(Crust.HANDTOSSED))
            {
                return 15.99;
            }
        }
        else if(this.getSize() == Size.MEDIUM)
        {
            if(this.getCrust().equals(Crust.STUFFED) ||
                    this.getCrust().equals(Crust.HANDTOSSED))
            {
                return 17.99;
            }
        }
        else {
            if(this.getCrust().equals(Crust.STUFFED) ||
                    this.getCrust().equals(Crust.HANDTOSSED))
            {
                return 19.99;
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
        return "Meatzza";
    }

    /**
     *
     * @return the toString method
     */
    @Override
    public String toString()
    {
        String style = "";
        if(this.getCrust().equals(Crust.STUFFED))
        {
            style = "(Chicago Style - Stuffed)";
        }
        else{
            style = "(New York Style - Hand tossed)";
        }


        return "Meatzza" + style + ", " + getToppingsStr() + this.getSize().toString() + " $" + price();
    }

}
