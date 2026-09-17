public class Pizza {
    private String nombre;
    private String[] ingredientes; // Arreglo obligatorio de tamaño fijo 3

    // Constructor que captura el nombre y los 3 ingredientes
    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.ingredientes = new String[3];
        
        if (ingredientes != null) {
            for (int i = 0; i < Math.min(ingredientes.length, 3); i++) {
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

