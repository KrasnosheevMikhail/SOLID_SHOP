import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final String ITEM_ADDED_MESSAGE = "Товар успешно добавлен!";
    // Принцип Magics. В случае локализации программы не нужно будет менять исходный код.
    public static final String ITEM_NOT_FOUND_MESSAGE = "Товар не найден";
    public static final String ITEM_DELETED_MESSAGE = "Товар удалён из корзины.";
    public static final String ITEM_ENTERED_NAME_MESSAGE = "Введите название товара.";
    public static final String ENTERED_MAX_MIN_PRICE_MESSAGE
            = "Введите минимальную и максимальную цену через пробел";
    public static final String ENTERED_KEYWORD_MESSAGE = "Введите ключевое слово для поиска в каталоге";
    public static final String TOTAL_COST_MESSAGE = "Общая сумма: %.2f%n";


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Basket userBasket = new Basket();
        CatalogServices catalog = new CatalogServices(Arrays.asList(new Bread(),
                new TV(), new Milk(), new Boombox()));
        //Liskov substitution principle - наследники класса Product полностью играют роль предка

        catalog.printCatalog(); //принцип DRY - повторяющийся вывод списка продуктов на экран выносим в отдельный метод

        int choice;
        while (true) {
            System.out.println("Выберите действие: \n" +
                    "1. Добавить в товар в корзину\n" +
                    "2. Убрать товар из корзины\n" +
                    "3. Поиск товаров по ценовому диапазону\n" +
                    "4. Поиск товара по ключевому слову\n" +
                    "5. Посмотреть список товаров в корзине\n" +
                    "6. Узнать итоговую стоимость товаров в корзине\n" +
                    "7. Выход");

            choice = Integer.parseInt(scanner.nextLine());
            if (choice == 7) break;

            switch (choice) {
                case (1):
                    System.out.println(ITEM_ENTERED_NAME_MESSAGE);
                    Product foundProduct = catalog.findByName(scanner.next());

                    if (foundProduct != null) {
                        userBasket.addItem(foundProduct);
                        System.out.println(ITEM_ADDED_MESSAGE);
                    } else {
                        System.out.println(ITEM_NOT_FOUND_MESSAGE);
                    }
                    break;
                case (2):
                    System.out.print(ITEM_ENTERED_NAME_MESSAGE);
                    if (userBasket.removeItem(scanner.nextLine())) {
                        System.out.println(ITEM_DELETED_MESSAGE);
                    } else {
                        System.out.println(ITEM_NOT_FOUND_MESSAGE);
                    }
                    break;
                case (3):
                    System.out.println(ENTERED_MAX_MIN_PRICE_MESSAGE);
                    catalog.findByPriceRange(scanner.nextInt(), scanner.nextInt());
                    break;
                case (4):
                    System.out.println(ENTERED_KEYWORD_MESSAGE);
                    catalog.findByKeyword(scanner.next());
                    break;
                case (5):
                    userBasket.getItems().forEach(item -> System.out.println(item.getName()));
                    break;
                case (6):
                    System.out.printf(TOTAL_COST_MESSAGE, userBasket.calculateTotalCost());
                    break;

            }
        }


    }
}

