import java.util.*;

// Интерфейсы для продуктов
interface Device {
    void working();
}

interface Food {
    void eat();
}

abstract class Product {
    protected String name;
    protected double price;

    // Конструктор для удобства создания объектов
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + ": $" + price;
    }
}

class Bread extends Product implements Food {
    public Bread() {
        super("Хлеб", 34.0); // Цена хлеба фиксирована
    }

    @Override
    public void eat() {
        System.out.println("Хлеб съеден");
    }
}

class TV extends Product implements Device {
    public TV() {
        super("Телевизор", 45_000.0); // Цена телевизора фиксирована
    }

    @Override
    public void working() {
        System.out.println("Телевизор исправен");
    }
}

class Boombox extends Product implements Device {
    public Boombox() {
        super("Бумбокс", 17_000.0); // Цена бумбокса фиксирована
    }

    @Override
    public void working() {
        System.out.println("Бумбокс играет музыку");
    }
}

class Milk extends Product implements Food {
    public Milk() {
        super("Молоко", 80.0); // Цена молока фиксирована
    }

    @Override
    public void eat() {
        System.out.println("Молоко выпито");
    }
}

class CatalogServices {
    List<Product> products;

    public CatalogServices(List<Product> products) {
        this.products = products;
    }

    public void printCatalog() {
        for (Product product : products)
            System.out.println(product.toString());
    }

    public Product findByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Product> findByKeyword(String keyword) {
        return products.stream()
                .filter(p -> p.getName().contains(keyword))
                .collect(Collectors.toList());
    }
}

class Basket {
    private Set<Product> items = new HashSet<>();

    public boolean addItem(Product item) {
        return items.add(item);
    }

    public boolean removeItem(String name) {
        Optional<Product> foundProduct = items.stream()
                .filter(p -> p.getName().equals(name))
                .findAny();

        if (foundProduct.isPresent()) {
            items.remove(foundProduct.get());
            return true;
        }
        return false;
    }

    public Collection<Product> getItems() {
        return items;
    }

    public double calculateTotalCost() {
        return items.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Basket basket = new Basket();
        CatalogServices catalog = new CatalogServices(
                Arrays.asList(new Bread(), new TV(), new Milk(), new Boombox())
        );

        catalog.printCatalog(); // вывод текущего каталога

        int choice;
        do {
            System.out.println("\nВыберите действие:\n" +
                    "1. Добавить товар в корзину\n" +
                    "2. Удалить товар из корзины\n" +
                    "3. Найти товары по ценовому диапазону\n" +
                    "4. Найти товар по ключевому слову\n" +
                    "5. Просмотреть содержимое корзины\n" +
                    "6. Посчитать общую сумму товаров в корзине\n" +
                    "7. Завершить работу");

            try {
                choice = Integer.parseInt(scanner.nextLine()); // Безопаснее читать строку

                switch(choice) {
                    case 1:
                        System.out.print("Введите название товара: ");
                        String prodName = scanner.nextLine();
                        Product foundProd = catalog.findByName(prodName);

                        if (foundProd != null) {
                            basket.addItem(foundProd);
                            System.out.println("Товар успешно добавлен в корзину!");
                        } else {
                            System.out.println("Такой товар отсутствует в каталоге.");
                        }
                        break;

                    case 2:
                        System.out.print("Введите название товара для удаления: ");
                        String delProdName = scanner.nextLine();
                        if (basket.removeItem(delProdName)) {
                            System.out.println("Товар удалён из корзины.");
                        } else {
                            System.out.println("Товар не найден в корзине.");
                        }
                        break;

                    case 3:
                        System.out.print("Минимальная цена: ");
                        double minPrice = Double.parseDouble(scanner.nextLine());
                        System.out.print("Максимальная цена: ");
                        double maxPrice = Double.parseDouble(scanner.nextLine());
                        List<Product> rangeProducts = catalog.findByPriceRange(minPrice, maxPrice);
                        if (!rangeProducts.isEmpty()) {
                            System.out.println("Подходящие товары:");
                            rangeProducts.forEach(System.out::println);
                        } else {
                            System.out.println("Нет товаров в указанном диапазоне.");
                        }
                        break;

                    case 4:
                        System.out.print("Введите ключевое слово для поиска: ");
                        String keyWord = scanner.nextLine();
                        List<Product> searchResults = catalog.findByKeyword(keyWord);
                        if (!searchResults.isEmpty()) {
                            System.out.println("Найденные товары:");
                            searchResults.forEach(System.out::println);
                        } else {
                            System.out.println("По данному запросу ничего не найдено.");
                        }
                        break;

                    case 5:
                        System.out.println("Список товаров в корзине:");
                        basket.getItems().forEach(System.out::println);
                        break;

                    case 6:
                        System.out.printf("Итоговая стоимость товаров в корзине: %.2f руб.\n",
                                basket.calculateTotalCost());
                        break;

                    default:
                        if (choice != 7) {
                            System.out.println("Неверный выбор. Повторите попытку.");
                        }
                }
            } catch(NumberFormatException e) {
                System.out.println("Ошибка ввода числа. Попробуйте снова.");
            }
        } while (choice != 7);

        System.out.println("Программа завершена.");
    }
}
