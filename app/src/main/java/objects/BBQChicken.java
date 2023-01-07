package objects;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Describes the BBQ Chicken pizza
 */
public class BBQChicken extends Pizza{
    private static final ArrayList<Topping> BBQChickToppings = new ArrayList<>(
            Arrays.asList(Topping.BBQCHICKEN, Topping.GREENPEPPER,
                    Topping.PROVOLONE, Topping.CHEDDAR)
    );

    /**
     * default constructor that sets all the toppings
     */
    public BBQChicken(){
        super();
        this.setToppings(BBQChickToppings);
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
            if(this.getCrust().equals(Crust.PAN) ||
                    this.getCrust().equals(Crust.THIN))
            {
                return 13.99;
            }
        }
        else if(this.getSize() == Size.MEDIUM)
        {
            if(this.getCrust().equals(Crust.PAN) ||
                    this.getCrust().equals(Crust.THIN))
            {
                return 15.99;
            }
        }
        else {
            if(this.getCrust().equals(Crust.PAN) ||
                    this.getCrust().equals(Crust.THIN))
            {
                return 17.99;
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
        return "BBQ Chicken";
    }

    /**
     *
     * @return the toString method
     */
    @Override
    public String toString()
    {
        String style = "";
        if(this.getCrust().equals(Crust.PAN))
        {
            style = "(Chicago Style - Pan)";
        }
        else{
            style = "(New York Style - Thin)";
        }


        return "BBQ Chicken" + style + ", " + getToppingsStr() + this.getSize().toString() + " $" + price();
    }

}
