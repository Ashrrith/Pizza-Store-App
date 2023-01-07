package objects;

import java.text.DecimalFormat;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * defines the build your own pizza which extends the pizza class
 */
public class BuildYourOwn extends Pizza{

    /**
     * default constructor
     */
    public BuildYourOwn() {
        super();
    }

    /**
     * adds a topping
     * @param obj Topping to be added
     * @return true if added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(this.getToppings().size() == 7 || !(obj instanceof Topping)
        || this.getToppings().contains((Topping) obj))
            return false;

        this.getToppings().add((Topping) obj);

        return true;
    }

    /**
     * removes a topping
     * @param obj topping to be removed
     * @return
     */
    @Override
    public boolean remove(Object obj) {
        if(this.getToppings().size() == 0 || !(obj instanceof Topping))
            return false;

        this.getToppings().remove((Topping) obj);

        return true;
    }

    /**
     *
     * @return the prices based on size and number of toppings
     */
    @Override
    public double price() {
        if(this.getSize() == Size.SMALL)
        {
            if(this.getCrust().equals(Crust.PAN) ||
                    this.getCrust().equals(Crust.HANDTOSSED))
            {
                return (this.getToppings().size()*1.59) + 8.99;
            }
        }
        else if(this.getSize() == Size.MEDIUM)
        {
            if(this.getCrust().equals(Crust.PAN) ||
                    this.getCrust().equals(Crust.HANDTOSSED))
            {
                return (this.getToppings().size()*1.59) + 10.99;
            }
        }
        else {
            if(this.getCrust().equals(Crust.PAN) ||
                    this.getCrust().equals(Crust.HANDTOSSED))
            {
                return (this.getToppings().size()*1.59) + 12.99;
            }
        }
        return 0;
    }

    /**
     *
     * @return string representation of buildyourown class
     */
    @Override
    public String pizzaFlavor() {
        return "Build your own";
    }


    /**
     *
     * @return toString which returns the price, crust, and size
     */
    @Override
    public String toString()
    {
        final DecimalFormat df = new DecimalFormat("0.00");
        String style = "";
        if(this.getCrust().equals(Crust.PAN))
        {
            style = "(Chicago Style - Pan)";
        }
        else{
            style = "(New York Style - Hand tossed)";
        }


        return "Build your own" + style + ", " + getToppingsStr()
                + this.getSize().toString() + " $" + df.format(price());
    }



}
