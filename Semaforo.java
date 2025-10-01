import java.util.concurrent.Semaphore;

class ContadorSemaforo {
    private int contador = 0;
    private final Semaphore sem = new Semaphore(1); // solo un hilo entra

    public void incrementar(String nombreHilo) {
        try {
            sem.acquire(); // sección crítica
            if (contador < 20) {
                contador++;
                System.out.println("Semáforo-" + nombreHilo + " incrementó contador: " + contador);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }

    public int getContador() {
        return contador;
    }
}

public class Semaforo {
    public static void main(String[] args) throws InterruptedException {
        ContadorSemaforo cs = new ContadorSemaforo();
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            String nombre = "Hilo-" + (i + 1);
            hilos[i] = new Thread(() -> {
                while (cs.getContador() < 20) {
                    cs.incrementar(nombre);
                    try {
                        Thread.sleep(100); // pequeña pausa
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

        System.out.println("Valor final con semáforo: " + cs.getContador());
    }
}
