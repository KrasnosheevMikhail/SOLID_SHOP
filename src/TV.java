public class TV implements Device, Product {
    private final String name = "Телевизор";
    private final int price = 45000;


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
    public void working() {
        System.out.println("Телевизор исправен");
    }
}
