public class Pila {
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    // 1. push(): Inserta un objeto Pizza en el tope de la pila
    public void push(Pizza pizza) {
        Nodo nuevoNodo = new Nodo(pizza);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
    }

    // 2. pop(): Retira el objeto del tope y devuelve su contenido
    public Pizza pop() {
        if (isEmpty()) {
            return null;
        }
        Pizza pizzaDesapilada = tope.getDato();
        tope = tope.getSiguiente();
        return pizzaDesapilada;
    }

    // 3. peek(): Visualiza la pizza en el tope sin retirarla
    public Pizza peek() {
        if (isEmpty()) {
            return null;
        }
        return tope.getDato();
    }

    // 4. isEmpty(): Valida si la pila se encuentra vacía
    public boolean isEmpty() {
        return tope == null;
    }

    // Método auxiliar para limpiar la pila de rehacer al registrar un nuevo pedido
    public void vaciar() {
        tope = null;
    }
}