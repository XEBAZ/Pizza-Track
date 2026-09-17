public class Nodo {
    private Pizza dato;
    private Nodo siguiente;

    public Nodo(Pizza dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Pizza getDato() {
        return dato;
    }

    public void setDato(Pizza dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}