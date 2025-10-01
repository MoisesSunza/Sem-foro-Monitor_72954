class ContadorMonitor {
    private int contador = 0;

    public synchronized void incrementar(String nombreHilo) {
        if (contador < 20) {
            contador++;
            System.out.println("Monitor-" + nombreHilo + " incrementó contador: " + contador);
        }
    }

    public synchronized int getContador() {
        return contador;
    }
}

public class Monitor {
    public static void main(String[] args) throws InterruptedException {
        ContadorMonitor cm = new ContadorMonitor();
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            String nombre = "Hilo-" + (i + 1);
            hilos[i] = new Thread(() -> {
                while (cm.getContador() < 20) {
                    cm.incrementar(nombre);
                    try {
                        Thread.sleep(100); // pausa
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
