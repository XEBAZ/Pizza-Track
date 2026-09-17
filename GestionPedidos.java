public class GestionPedidos {
    private Pila pilaPrincipal; // Pila de Pedidos Activos / Undo
    private Pila pilaSecundaria; // Pila de Pedidos Deshechos / Redo

    public GestionPedidos() {
        this.pilaPrincipal = new Pila();
        this.pilaSecundaria = new Pila();
    }

    // Registrar Pedido (Escribir): Realiza push() en la Pila Principal
    public void registrarPedido(Pizza pizza) {
        pilaPrincipal.push(pizza);
        // Al ingresar una nueva acción, se limpia la historia de rehacer
        pilaSecundaria.vaciar();
        System.out.println("\n[+] Pedido registrado exitosamente: " + pizza.getNombre());
    }

    // Deshacer (Undo): pop() de Pila Principal -> push() a Pila Secundaria
    public void deshacer() {
        if (pilaPrincipal.isEmpty()) {
            System.out.println("\n[!] No hay pedidos para deshacer.");
        } else {
            Pizza pedidoDeshecho = pilaPrincipal.pop();
            pilaSecundaria.push(pedidoDeshecho);
            System.out.println("\n[<-] Se deshizo el pedido: " + pedidoDeshecho.getNombre());
        }
    }

    // Rehacer (Redo): pop() de Pila Secundaria -> push() a Pila Principal
    public void rehacer() {
        if (pilaSecundaria.isEmpty()) {
            System.out.println("\n[!] No hay pedidos para rehacer.");
        } else {
            Pizza pedidoRecuperado = pilaSecundaria.pop();
            pilaPrincipal.push(pedidoRecuperado);
            System.out.println("\n[->] Se rehizo el pedido: " + pedidoRecuperado.getNombre());
        }
    }

    // Mostrar Pedido Actual: Utiliza peek() en la Pila Principal
    public void mostrarPedidoActual() {
        if (pilaPrincipal.isEmpty()) {
            System.out.println("\n[i] No hay pedidos activos en cola de producción.");
        } else {
            Pizza actual = pilaPrincipal.peek();
            System.out.println("\n[i] Pedido actual listo para producción: " + actual);
        }
    }
}