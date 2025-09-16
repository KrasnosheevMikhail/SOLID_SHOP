public interface Food {
    void eat();
}
//Interface Segregation Principle - применяем разные интерфейсы вместо одного большого
//Dependency Inversion Principle - разделив интерфейсы мы не зависим от их общей функциональности,
// мы можем поменять один интерфейс на другой