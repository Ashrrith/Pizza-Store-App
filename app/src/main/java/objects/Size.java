package objects;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * Size enum class that defines small, medium, and large sizes
 */
public enum Size {
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private final String sizeStr;

    /**
     *
     * @param sizeStr the string name of the size
     */
    Size(String sizeStr)
    {
        this.sizeStr = sizeStr;
    }
}
