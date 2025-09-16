import java.util.List;

public class CatalogServices {
    private List<Product> products;

    public CatalogServices(List<Product> products) {
        this.products = products;
    }

    public List<Product> findByPriceRange(int minPrice, int maxPrice) {
        return products.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .toList();
    }

    public List<Product> findByKeyword(String keyword) {
        return products.stream()
                .filter(product -> product.getName().contains(keyword))
                .toList();
    }

    public void printCatalog() {
        System.out.println("Каталог товаров:");
        for (Product product : products)
            System.out.println(product.toString());
    }

    /*public Product findByName(String name) {
        for (Product p : products) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }*/
    public Product findByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
