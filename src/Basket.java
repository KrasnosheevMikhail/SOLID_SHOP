import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Product> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public boolean removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Product> getItems() {
        return items;
    }

    public double calculateTotalCost() {
        return items.stream().mapToInt(Product::getPrice).sum();

    }


}
