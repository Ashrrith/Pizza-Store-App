package objects;

/**
 * @author Visshnusaiashrrith Rachakunta, Bhuvan Vinodh
 * New York flavor pizza that implements the Pizza factory
 */
public class NYPizza implements PizzaFactory{

    /**
     *
     * @return a deluxe pizza with set crust
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.BROOKLYN);
        return pizza;
    }

    /**
     *
     * @return a meatzza pizza with a set crust
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.HANDTOSSED);
        return pizza;
    }

    /**
     *
     * @return a bbq pizza with a set crust
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.THIN);
        return pizza;
    }

    /**
     *
     * @return a create your own pizza with set crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.HANDTOSSED);
        return pizza;
    }
}
