# Semáforo y Monitor

**P1** - Desarrolle un software con concurrencia usando exclusión mutua con monitores y semáforos

## Introducción

En la programación concurrente, múltiples hilos pueden acceder de manera simultánea a recursos compartidos. Cuando esto sucede sin mecanismos de control, aparece el problema conocido como **condición de carrera**. Este ocurre cuando el resultado final depende del orden en que los hilos intercalan sus instrucciones, generando resultados inconsistentes e impredecibles.

Un ejemplo clásico es el incremento de un contador compartido: si varios hilos lo modifican al mismo tiempo sin sincronización, el resultado final puede ser menor al esperado. Este problema no solo compromete la **corrección** de un programa, sino también su **seguridad y confiabilidad**.

Para resolverlo se aplican mecanismos de **exclusión mutua**, como **semáforos** y **monitores**, que aseguran que únicamente un hilo a la vez ejecute la sección crítica, evitando errores y garantizando resultados consistentes.



## Semáforos y Monitores

Los semáforos y los monitores son mecanismos fundamentales de sincronización en la programación concurrente, diseñados para resolver el problema de la condición de carrera y garantizar la exclusión mutua en el acceso a recursos compartidos.

* **Semáforo:** Un semáforo es una variable entera protegida que actúa como contador de permisos. Fue introducido por Edsger Dijkstra en 1965 y desde entonces se ha convertido en una herramienta esencial para la coordinación entre hilos. En Java, la clase Semaphore implementa esta funcionalidad. Un semáforo puede inicializarse con uno o varios permisos. Cuando un hilo desea acceder a un recurso, debe ejecutar acquire(). Si hay permisos disponibles, el semáforo los concede y el hilo continúa; si no, el hilo queda bloqueado hasta que otro hilo ejecute release(), liberando un permiso. Este mecanismo asegura que solo un número controlado de hilos acceda simultáneamente al recurso, lo que resulta muy útil en problemas de concurrencia con acceso limitado, como la gestión de conexiones o el control de buffers.

En el programa se crea un objeto **Semaphore** con un solo permiso (new Semaphore(1)), lo que garantiza que únicamente un hilo pueda entrar a la sección crítica en un momento dado. Cada hilo, al querer incrementar el contador compartido, primero llama a acquire() para obtener el permiso; si otro hilo ya lo tiene, debe esperar. Una vez dentro, el hilo lee el valor actual del contador, lo incrementa en 1, e imprime el valor anterior y el nuevo junto con su nombre. Al salir, libera el permiso con release(), permitiendo que otro hilo pueda continuar. Este mecanismo asegura que no haya condiciones de carrera, y que los incrementos ocurran de manera controlada y consistente hasta que el contador llega a 20.

![alt text](https://github.com/MoisesSunza/Sem-foro-Monitor_72954/blob/main/Semaforo.png)

* **Monitor:** Un monitor es una abstracción de mayor nivel que combina tanto datos como procedimientos sincronizados. En Java, cada objeto puede funcionar como monitor gracias a la palabra clave synchronized. Un método marcado como sincronizado solo puede ser ejecutado por un hilo a la vez, mientras que los demás deben esperar hasta que la ejecución finalice. Los monitores también ofrecen mecanismos de comunicación entre hilos mediante wait(), notify() y notifyAll(), lo que permite una coordinación más estructurada.

En este se implementa la exclusión mutua con la palabra clave **synchronized**. El método incrementar() está marcado como synchronized, lo que significa que solo un hilo puede ejecutarlo a la vez mientras los demás esperan su turno. Dentro de este método, el hilo obtiene el valor actual del contador, lo incrementa en 1 e imprime su nombre junto con el valor anterior y el nuevo. A diferencia del semáforo, aquí la sincronización es gestionada automáticamente por el monitor implícito de Java asociado al objeto. Esto permite una forma más sencilla y legible de controlar el acceso concurrente. Así, el contador se incrementa de manera segura y consistente hasta alcanzar 20, sin que ocurra corrupción de datos.

![alt text](https://github.com/MoisesSunza/Sem-foro-Monitor_72954/blob/main/Monitor.png)

Los semáforos son más flexibles para controlar recursos múltiples, mientras que los monitores simplifican la implementación al ofrecer sincronización automática de métodos.



## Conclusiones

La exclusión mutua es un elemento esencial en la programación concurrente, ya que previene errores derivados de la condición de carrera cuando varios hilos intentan acceder de forma simultánea a recursos compartidos. Sin mecanismos de control, los programas se vuelven impredecibles y poco confiables.

Los semáforos y los monitores son dos enfoques complementarios que permiten resolver este problema. Los semáforos ofrecen un control más flexible y general, ya que permiten administrar un número limitado de permisos y regular el acceso a varios recursos de manera precisa. Por su parte, los monitores simplifican el diseño de los programas al encapsular automáticamente la sincronización de métodos, garantizando que solo un hilo ejecute una sección crítica a la vez.

La correcta utilización de ambos mecanismos asegura que los resultados sean consistentes, confiables y reproducibles, incluso en entornos con múltiples hilos de ejecución. Además, fomentan el desarrollo de aplicaciones robustas que aprovechan la concurrencia sin sacrificar estabilidad.

