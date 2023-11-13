import models.Monitor;
import threads.Consumidor;
import threads.Productor;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        Thread prod = new Thread(new Productor(monitor));
        prod.setName("Productor");
        prod.start();

        Thread cons =  new Thread(new Consumidor(monitor));
        cons.setName("Consumidor");
        cons.start();

        try {
            prod.join();
            cons.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}