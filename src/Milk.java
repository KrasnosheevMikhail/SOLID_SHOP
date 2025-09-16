public class Milk extends Product implements Food {

    //Open Closed Principle - имплементируя интерфейс Food мы добавляем
    // классу функциональности, не изменяя его код

    private final String name = "Молоко";
    private final int price = 80;


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
        System.out.println("Молоко выпито");
    }
}
