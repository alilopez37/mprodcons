package threads;

import models.Monitor;

public class Productor implements Runnable{
    private Monitor monitor;
    public Productor(Monitor monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {
        while (true){
            monitor.insertar();
        }
    }
}
