class ContadorMonitor {
    private int contador = 0;

    public synchronized void incrementar() {
        int aux = contador;
        aux++;
        try {
            Thread.sleep(10); // simular demora
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contador = aux;
    }

    public synchronized int getContador() {
        return contador;
    }
}

public class MainMonitor {
    public static void main(String[] args) throws InterruptedException {
        ContadorMonitor cm = new ContadorMonitor();
        Thread[] hilos = new Thread[10];

        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    cm.incrementar();
                }
            });
            hilos[i].start();
        }

        for (Thread t : hilos) {
            t.join();
        }

        System.out.println("Valor final con monitor: " + cm.getContador());
    }
}
