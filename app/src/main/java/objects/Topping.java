package objects;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Topping is an enum class that defines 13 unique toppings
 */
public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREENPEPPER("Green Pepper"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    BBQCHICKEN("BBQ Chicken"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    BEEF("Beef"),
    HAM("Ham"),
    CHICKEN("Chicken"),
    BLACKOLIVES("Black Olives"),
    BANANAPEPPERS("Banana Peppers");

    private final String toppingName;

    /**
     *
     * @param toppingName the string name of the topping
     */
    Topping(String toppingName)
    {
        this.toppingName = toppingName;
    }


    /**
     *
     * @return the string representation of the topping
     */
    public String getStringName()
    {
        return toppingName;
    }

    /**
     *
     * @return an ArrayList of all the toppings from the topping class
     */
    public static ArrayList<String> listOfAllToppings(){
        final ArrayList<String> allToppings = new ArrayList<>(
                Arrays.asList("Sausage", "Pepperoni",
                        "Green Pepper", "Onion", "Mushroom",
                        "BBQ Chicken", "Provolone", "Cheddar",
                        "Beef", "Ham", "Chicken", "Black Olives", "Banana Peppers")
        );

        return allToppings;
    }

}
