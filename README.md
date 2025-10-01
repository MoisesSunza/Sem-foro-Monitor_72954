# Sem-foro-Monitor_72954

**Tema:** Condición de carrera, Semáforos y Monitores

## Introducción

En la programación concurrente, múltiples hilos pueden acceder de manera simultánea a recursos compartidos. Cuando esto sucede sin mecanismos de control, aparece el problema conocido como **condición de carrera**. Este ocurre cuando el resultado final depende del orden en que los hilos intercalan sus instrucciones, generando resultados inconsistentes e impredecibles.

Un ejemplo clásico es el incremento de un contador compartido: si varios hilos lo modifican al mismo tiempo sin sincronización, el resultado final puede ser menor al esperado. Este problema no solo compromete la **corrección** de un programa, sino también su **seguridad y confiabilidad**.

Para resolverlo se aplican mecanismos de **exclusión mutua**, como **semáforos** y **monitores**, que aseguran que únicamente un hilo a la vez ejecute la sección crítica, evitando errores y garantizando resultados consistentes.

---

## Semáforos y Monitores en Java

* **Semáforo:** Es una estructura de sincronización que controla el acceso concurrente mediante un contador de permisos. En Java se implementa con la clase `Semaphore`. Al usar `acquire()` un hilo intenta obtener un permiso; si no hay disponible, queda bloqueado hasta que otro hilo lo libere con `release()`.

* **Monitor:** Es una abstracción de más alto nivel que encapsula variables y métodos sincronizados. En Java, la palabra clave `synchronized` convierte un método en sección crítica, garantizando que solo un hilo lo ejecute a la vez.

Los semáforos son más flexibles para controlar recursos múltiples, mientras que los monitores simplifican la implementación al ofrecer sincronización automática de métodos.

---

## Código

* [Código con Semáforo](MainSemaforo.java)
* [Código con Monitor](MainMonitor.java)

Ambos ejemplos crean 10 hilos que incrementan un contador compartido 100 veces cada uno.
El resultado esperado es **1000**, y gracias a la exclusión mutua, el valor siempre es correcto.

---

## Conclusiones

La condición de carrera evidencia la necesidad de controlar el acceso concurrente a los recursos.
Los **semáforos** y **monitores** son mecanismos esenciales para implementar exclusión mutua en Java:

* Los semáforos ofrecen mayor **flexibilidad** en problemas de conteo o coordinación.
* Los monitores proporcionan **simplicidad** y claridad en el diseño al sincronizar métodos directamente.

El uso adecuado de estos mecanismos asegura programas concurrentes más robustos, seguros y predecibles.

---

## Capturas de pantalla
