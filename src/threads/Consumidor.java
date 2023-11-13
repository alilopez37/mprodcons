package threads;

import models.Monitor;

public class Consumidor implements Runnable{
    private Monitor monitor;
    public Consumidor(Monitor monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {
        while (true){
            monitor.extraer();
        }
    }
}
