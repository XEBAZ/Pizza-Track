# Pizza-Track
Sistema de gestión de pedidos de pizzería utilizando Pilas basadas en Listas Ligadas en Java.
### `Nodo.java`

```
```
# Pizza-Track 🍕 - Sistema de Gestión de Pedidos

## 🎯 Objetivo del Proyecto
Desarrollar una aplicación en Java que simule el sistema de gestión de pedidos de una pizzería (**Pizza-Track**), aplicando estructuras de datos lineales avanzadas. El objetivo principal es gestionar el flujo de trabajo mediante dos pilas manuales basadas en **Listas Ligadas (Nodos)** sin el uso de librerías nativas como `java.util.Stack`, permitiendo realizar operaciones de registro, **Deshacer (Undo)** y **Rehacer (Redo)** de pedidos.

---

## 🏗️ Arquitectura del Sistema

El proyecto está diseñado bajo una arquitectura modular compuesta por 5 clases:

* **`Pizza.java`**: Modelo de datos del pedido. Contiene el nombre y un **arreglo de tamaño fijo (3)** para almacenar los ingredientes.
* **`Nodo.java`**: Representa el elemento de la lista ligada, almacenando el objeto `Pizza` y la referencia (`siguiente`) al nodo inferior.
* **`Pila.java`**: Implementación de la pila manual con los métodos requeridos: `push()`, `pop()`, `peek()` e `isEmpty()`.
* **`GestionPedidos.java`**: Controlador principal que coordina:
  * **Pila Principal (Undo):** Mantiene la lista de pedidos activos.
  * **Pila Secundaria (Redo):** Almacena temporalmente los pedidos deshechos para su recuperación.
* **`PizzaTrack.java`**: Interfaz de usuario interactiva por consola.

---

## 🔗 Explicación de la Lógica de Punteros

La lista ligada maneja las conexiones entre nodos mediante referencias en memoria:
* **`tope`**: Puntero principal que apunta al nodo en la cima de la pila.
* **`push()`**: El nuevo nodo ajusta su puntero `siguiente` hacia el `tope` actual, y posteriormente `tope` se reasigna al nuevo nodo.
* **`pop()`**: Extrae el valor de `tope` y desplaza el puntero al nodo siguiente (`tope = tope.getSiguiente()`), permitiendo que el nodo desvinculado sea liberado por el Garbage Collector.

---

## 🚀 Instrucciones de Ejecución

### Prerrequisitos
* Tener instalado el **JDK de Java** (versión 8 o superior).

### Pasos para Ejecutar desde la Consola

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/TU_USUARIO/Pizza-Track.git
   cd Pizza-Track

```

1. **Compilar los archivos Java:**

```
javac *.java

```

1. **Ejecutar la aplicación:**

```
java PizzaTrack
```
public class Nodo {
    private Pizza dato;
    
    // PUNTERO / REFERENCIA: Guarda la dirección del siguiente nodo en la lista ligada.
    // Si es el último nodo de la pila, su valor es 'null'.
    private Nodo siguiente;

    public Nodo(Pizza dato) {
        this.dato = dato;
        // Al instanciar un nodo, su puntero "siguiente" inicia apuntando a null
        this.siguiente = null;
    }

    public Pizza getDato() {
        return dato;
    }

    public void setDato(Pizza dato) {
        this.dato = dato;
    }

    // Retorna la referencia/puntero hacia el siguiente nodo
    public Nodo getSiguiente() {
        return siguiente;
    }

    // Modifica la dirección a la que apunta este nodo
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

```

---

### `Pila.java` (Lógica de Punteros en la Lista Ligada)

```
public class Pila {
    // PUNTERO DE CABECERA (TOPE): Mantiene la referencia al nodo superior de la pila.
    // Si 'tope == null', indica que la pila está vacía (no hay memoria enlazada).
    private Nodo tope;

    public Pila() {
        this.tope = null; // Inicialización con puntero nulo
    }

    /**
     * OPERACIÓN PUSH (APILAR):
     * Lógica de punteros:
     * 1. Se reserva memoria para un 'nuevoNodo'.
     * 2. El puntero 'siguiente' del nuevo nodo se hace apuntar a donde apunta actualmente 'tope'.
     * 3. El puntero 'tope' se reasigna para apuntar al 'nuevoNodo'.
     * Complejidad: O(1) tiempo constante.
     */
    public void push(Pizza pizza) {
        Nodo nuevoNodo = new Nodo(pizza);
        
        // Enlace: El nuevo nodo apunta hacia abajo (al antiguo tope)
        nuevoNodo.setSiguiente(tope);
        
        // Redirección: 'tope' ahora señala al nuevo nodo
        tope = nuevoNodo;
    }

    /**
     * OPERACIÓN POP (DESAPILAR):
     * Lógica de punteros:
     * 1. Se obtiene el objeto 'Pizza' referenciado por el puntero 'tope'.
     * 2. El puntero 'tope' avanza hacia el nodo inferior asignándole 'tope.getSiguiente()'.
     * 3. El nodo desvinculado pierde todas sus referencias externas, permitiendo que el
     *    Garbage Collector de Java libere automáticamente su memoria.
     */
    public Pizza pop() {
        if (isEmpty()) {
            return null; // Sin punteros que manipular
        }
        
        // Extraemos los datos del nodo referenciado por el tope
        Pizza pizzaDesapilada = tope.getDato();
        
        // Desplazamiento del puntero 'tope' al siguiente nodo en la lista
        tope = tope.getSiguiente();
        
        return pizzaDesapilada;
    }

    /**
     * OPERACIÓN PEEK (VISUALIZAR CIMA):
     * Accede al contenido del nodo apuntado por 'tope' sin alterar las referencias/punteros.
     */
    public Pizza peek() {
        if (isEmpty()) {
            return null;
        }
        return tope.getDato();
    }

    /**
     * OPERACIÓN IS_EMPTY (VALIDAR VACÍO):
     * Evalúa si el puntero 'tope' no está referenciando a ningún objeto en memoria.
     */
    public boolean isEmpty() {
        return tope == null;
    }

    /**
     * OPERACIÓN VACIAR:
     * Al romper la referencia del puntero 'tope' asignando 'null', toda la cadena de
     * nodos encadenados queda inaccesible y la memoria es reciclada.
     */
    public void vaciar() {
        tope = null;
    }
}

```

---

### `Pizza.java`

```
public class Pizza {
    private String nombre;
    private String[] ingredientes; // Arreglo fijo de tamaño 3

    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.ingredientes = new String[3];
        
        if (ingredientes != null) {
            for (int i = 0; i &lt; Math.min(ingredientes.length, 3); i++) {
                this.ingredientes[i] = ingredientes[i];
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    @Override
    public String toString() {
        return "Pizza: " + nombre + " | Ingredientes: [" + 
               ingredientes[0] + ", " + ingredientes[1] + ", " + ingredientes[2] + "]";
    }
}

```

---

### `GestionPedidos.java`

```
public class GestionPedidos {
    // Dos pilas manuales que gestionan punteros de manera independiente
    private Pila pilaPrincipal;  // Pila Undo
    private Pila pilaSecundaria; // Pila Redo

    public GestionPedidos() {
        this.pilaPrincipal = new Pila();
        this.pilaSecundaria = new Pila();
    }

    public void registrarPedido(Pizza pizza) {
        pilaPrincipal.push(pizza);
        pilaSecundaria.vaciar(); // Al agregar un pedido nuevo se invalida la historia de rehacer
        System.out.println("\n[+] Pedido registrado exitosamente: " + pizza.getNombre());
    }

    // Undo: Puntero de Pila Principal decrementa -&gt; Puntero de Pila Secundaria incrementa
    public void deshacer() {
        if (pilaPrincipal.isEmpty()) {
            System.out.println("\n[!] No hay pedidos para deshacer.");
        } else {
            Pizza pedidoDeshecho = pilaPrincipal.pop();
            pilaSecundaria.push(pedidoDeshecho);
            System.out.println("\n[&lt;-] Se deshizo el pedido: " + pedidoDeshecho.getNombre());
        }
    }

    // Redo: Puntero de Pila Secundaria decrementa -&gt; Puntero de Pila Principal incrementa
    public void rehacer() {
        if (pilaSecundaria.isEmpty()) {
            System.out.println("\n[!] No hay pedidos para rehacer.");
        } else {
            Pizza pedidoRecuperado = pilaSecundaria.pop();
            pilaPrincipal.push(pedidoRecuperado);
            System.out.println("\n[-&gt;] Se rehizo el pedido: " + pedidoRecuperado.getNombre());
        }
    }

    public void mostrarPedidoActual() {
        if (pilaPrincipal.isEmpty()) {
            System.out.println("\n[i] No hay pedidos activos en cola de producción.");
        } else {
            Pizza actual = pilaPrincipal.peek();
            System.out.println("\n[i] Pedido actual listo para producción: " + actual);
        }
    }
}

```

---

### `PizzaTrack.java`

```
import java.util.Scanner;

public class PizzaTrack {
    public static void main(String[] args) {
        GestionPedidos gestion = new GestionPedidos();
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        System.out.println("=============================================");
        System.out.println("  SISTEMA DE GESTIÓN DE PEDIDOS - PIZZA-TRACK");
        System.out.println("=============================================");

        do {
            System.out.println("\n----------- MENÚ DE OPCIONES -----------");
            System.out.println("1. Registrar Pizza (Escribir)");
            System.out.println("2. Deshacer (Undo)");
            System.out.println("3. Rehacer (Redo)");
            System.out.println("4. Mostrar Pedido Actual");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n[!] Entrada inválida. Ingrese un número entero.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese el nombre de la pizza: ");
                    String nombre = scanner.nextLine();

                    String[] ingredientes = new String[3];
                    System.out.println("Ingrese exactamente 3 ingredientes:");
                    for (int i = 0; i &lt; 3; i++) {
                        System.out.print("  Ingrediente " + (i + 1) + ": ");
                        ingredientes[i] = scanner.nextLine();
                    }

                    Pizza nuevaPizza = new Pizza(nombre, ingredientes);
                    gestion.registrarPedido(nuevaPizza);
                    break;

                case 2:
                    gestion.deshacer();
                    break;

                case 3:
                    gestion.rehacer();
                    break;

                case 4:
                    gestion.mostrarPedidoActual();
                    break;

                case 0:
                    System.out.println("\nSaliendo del sistema Pizza-Track... ¡Hasta luego!");
                    break;

                default:
                    System.out.println("\n[!] Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
```
