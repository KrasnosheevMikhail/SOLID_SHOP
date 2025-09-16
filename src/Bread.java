public class Bread extends Product implements Food {
    private final String name = "Хлеб";
    private final int price = 34;
//Single Responsibility Principle - класс Bread описывает только один продукт, а не все.

    @Override
    public String toString() {
        return name + " - " + price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void eat() {
        System.out.println("Хлеб сЪеден");
    }
}
