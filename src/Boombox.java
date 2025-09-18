public class Boombox implements Device, Product{
    private final String name = "Бумбокс";
    private final int price = 17000;


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
        System.out.println("Бумбокс воспроизводит музыку");
    }
}
