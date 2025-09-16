import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Basket userBasket = new Basket();
        CatalogServices catalog = new CatalogServices(Arrays.asList(new Bread(),
                new TV(), new Milk(), new Boombox()));

        catalog.printCatalog();

        int input;
        while (true) {
            System.out.println("Выберите действие: \n" +
                    "1. Добавить в товар в корзину\n" +
                    "2. Убрать товар из корзины\n" +
                    "3. Поиск товаров по ценовому диапазону\n" +
                    "4. Поиск товара по ключевому слову\n" +
                    "5. Посмотреть список товаров в корзине\n" +
                    "6. Узнать итоговую стоимость товаров в корзине\n" +
                    "7. Выход");

            input = scanner.nextInt();
            if (input == 7) break;
            switch (input) {
                case (1):
                    System.out.println("Введите название продукта и цену через пробел");
                    Product foundProduct = catalog.findByName(scanner.next());

                    if (foundProduct != null) {
                        userBasket.addItem(foundProduct);
                        System.out.println("Товар успешно добавлен!");
                    } else {
                        System.out.println("Такой товар отсутствует в каталоге.");
                    }
                    break;
                case (2):
                    System.out.print("Введите название товара для удаления: ");
                    if (userBasket.removeItem(scanner.nextLine())) {
                        System.out.println("Товар удалён из корзины.");
                    } else {
                        System.out.println("Товар не найден в корзине.");
                    }
                    break;
                case (3):
                    System.out.println("Введите минимальную и максимальную цену через пробел");
                    catalog.findByPriceRange(scanner.nextInt(), scanner.nextInt());
                    break;
                case (4):
                    System.out.println("Введите ключевое слово для поиска в каталоге");
                    catalog.findByKeyword(scanner.next());
                    break;
                case (5):
                    userBasket.getItems().forEach(item -> System.out.println(item.getName()));
                    break;
                case (6):
                    System.out.printf("Общая сумма: %.2f%n", userBasket.calculateTotalCost());
                    break;

            }
        }


    }
}