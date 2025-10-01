import java.util.concurrent.Semaphore;

class ContadorSemaforo {
    private int contador = 0;
    private final Semaphore sem = new Semaphore(1);

    public void incrementar() {
        try {
            sem.acquire();
            int aux = contador;
            aux++;
            Thread.sleep(10);
            contador = aux;
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

public class MainSemaforo {
    public static void main(String[] args) throws InterruptedException {
        ContadorSemaforo cs = new ContadorSemaforo();
        Thread[] hilos = new Thread[10];

        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    cs.incrementar();
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
