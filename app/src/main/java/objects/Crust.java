package objects;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Crust is an enum class that defines various crusts
 */
public enum Crust {

    DEEPDISH("Deep Dish"),
    BROOKLYN("Brooklyn"),
    PAN("Pan"),
    THIN("Thin"),
    STUFFED("Stuffed"),
    HANDTOSSED("Hand Tossed");

    private final String crustName;

    /**
     *
     * @param crustName the string name of the topping
     */
    Crust(String crustName)
    {
        this.crustName = crustName;
    }

    /**
     *
     * @return string representation of the crust object
     */
    public String getStringCrustName()
    {
        return crustName;
    }


    /**
     *
     * @param c crust being compared to
     * @return true if this crust name is equal to given crust's name
     */
    public boolean equals(Crust c)
    {
        return c.getStringCrustName().equalsIgnoreCase(this.crustName);
    }

}
