package models;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Monitor {
    private final int TOTAL = 10;
    private int[] buffer = new int[TOTAL];
    private int lleno;

    @Override
    public String toString() {
        return "Monitor{" +
                "buffer=" + Arrays.toString(buffer) +
                '}';
    }

    private Random random = new Random(System.currentTimeMillis());

    public Monitor(){
        lleno = 0;
        for (int i=0;i<TOTAL;i++)
            buffer[i] = 0;
    }
    public synchronized void insertar(){
        int valor;
        int indice;

        valor = random.nextInt(400) + 100;
        while (lleno == TOTAL ) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        indice = 0;
        while (buffer[indice] != 0)
            indice++;
        buffer[indice] = valor;
        System.out.println(Thread.currentThread().getName() + ":" + this.toString());
        lleno++;
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notify();
    }
    public synchronized void extraer() {
        int indice;
        int valor;
        while (lleno == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        indice = 0;
        while(buffer[indice] == 0)
            indice++;
        valor = buffer[indice];
        System.out.println(Thread.currentThread().getName() + ":" + this.toString());
        buffer[indice] = 0;
        lleno--;
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notify();
    }
}
