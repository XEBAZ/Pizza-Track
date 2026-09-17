import java.util.Scanner;

public class PizzaTrack {
    public static void main(String[] args) {
        GestionPedidos gestion = new GestionPedidos();
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        System.out.println("=============================================");
        System.out.println(" SISTEMA DE GESTIÓN DE PEDIDOS - PIZZA-TRACK");
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
                System.out.println("\n[!] Entrada inválida. Por favor, ingrese un número entero.");
                continue;
            }
            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese el nombre de la pizza: ");
                    String nombre = scanner.nextLine();
                    String[] ingredientes = new String[3];
                    System.out.println("Ingrese exactamente 3 ingredientes:");
                    for (int i = 0; i < 3; i++) {
                        System.out.print(" Ingrediente " + (i + 1) + ": ");
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