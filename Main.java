package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Main {
    private static ArrayList<UtilEscolar> listaUtilEscolar = new ArrayList<>();
    private static Map<String, UtilEscolar> mapUtilEscolar = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        while (!salir) {
            System.out.println("\n==== Lista de Pendientes ====");
            System.out.println("1. Agregar elementos");
            System.out.println("2. Modificar pendientes");
            System.out.println("3. Buscar elemento");
            if (listaUtilEscolar.size() > 0) {
                System.out.println("4. Eliminar elemento");
                System.out.println("5. Ordenar elementos (alfabéticamente)");
                System.out.println("6. Imprimir lista de pendientes");
            }
            System.out.println("X. Salir");

            System.out.print("\nElija una opción: ");
            String opcion = sc.nextLine().toUpperCase();

            switch (opcion) {
                case "1":
                    agregarElementos(sc);
                    break;
                case "2":
                    modificarPendientes(sc);
                    break;
                case "3":
                    buscarElemento(sc);
                    break;
                case "4":
                    eliminarElemento(sc);
                    break;
                case "5":
                    ordenarElementos();
                    break;
                case "6":
                    imprimirLista();
                    break;
                case "X":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void agregarElementos(Scanner sc) {
        System.out.print("\nIngrese la descripción del elemento: ");
        String descripcion = sc.nextLine();

        if (mapUtilEscolar.containsKey(descripcion.toUpperCase())) {
            System.out.println("El elemento ya existe en la lista de pendientes.");
            return;
        }

        System.out.print("Ingrese la cantidad a comprar: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        UtilEscolar utilEscolar = new UtilEscolar(descripcion, cantidad);
        listaUtilEscolar.add(utilEscolar);
        mapUtilEscolar.put(utilEscolar.getDescripcion(), utilEscolar);

        System.out.println("Elemento agregado correctamente.");
    }

    private static void modificarPendientes(Scanner sc) {
        if (listaUtilEscolar.isEmpty()) {
            System.out.println("La lista de pendientes está vacía.");
            return;
        }

        System.out.println("\nElija el elemento a modificar: ");

        for (int i = 0; i < listaUtilEscolar.size(); i++) {
            System.out.println((i+1) + ". " + listaUtilEscolar.get(i).obtenerDescripcionYPendiente());
        }

        System.out.print("\nIngrese el número correspondiente al elemento: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        if (opcion < 1 || opcion > listaUtilEscolar.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        UtilEscolar utilEscolar = listaUtilEscolar.get(opcion-1);

        System.out.println("\nElemento seleccionado: " + utilEscolar.obtenerDescripcionYPendiente());

        System.out.print("Ingrese la cantidad comprada: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        utilEscolar.modificarCantidadComprada(cantidad);

        System.out.println("Cantidad comprada actualizada correctamente.");
    }

    private static void buscarElemento(Scanner sc) {
        if (listaUtilEscolar.isEmpty()) {
            System.out.println("La lista de pendientes está vacía.");
            return;
        }

        System.out.print("\nIngrese la descripción del elemento a buscar: ");
        String descripcion = sc.nextLine();

        UtilEscolar utilEscolar = mapUtilEscolar.get(descripcion.toUpperCase());

        if (utilEscolar == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }

        String mensaje = utilEscolar.obtenerDescripcionYPendiente();

        if (utilEscolar.obtenerCantidadPendiente() < 0) {
            mensaje += " (Advertencia: Cantidad comprada excede la cantidad a comprar)";
        }

        System.out.println("\n" + mensaje);
    }

    private static void eliminarElemento(Scanner sc) {
        if (listaUtilEscolar.isEmpty()) {
            System.out.println("La lista de pendientes está vacía.");
            return;
        }

        System.out.println("\nElija el elemento a eliminar: ");

        for (int i = 0; i < listaUtilEscolar.size(); i++) {
            System.out.println((i+1) + ". " + listaUtilEscolar.get(i).obtenerDescripcionYPendiente());
        }

        System.out.print("\nIngrese el número correspondiente al elemento: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        if (opcion < 1 || opcion > listaUtilEscolar.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        UtilEscolar utilEscolar = listaUtilEscolar.get(opcion-1);

        listaUtilEscolar.remove(utilEscolar);
        mapUtilEscolar.remove(utilEscolar.getDescripcion());

        System.out.println("Elemento eliminado correctamente.");
    }

private static void ordenarElementos() {
        if (listaUtilEscolar.isEmpty()) {
            System.out.println("La lista de pendientes está vacía.");
            return;
        }

        for (int i = 1; i < listaUtilEscolar.size(); i++) {
            UtilEscolar actual = listaUtilEscolar.get(i);
            int j = i - 1;

            while (j >= 0 && listaUtilEscolar.get(j).getDescripcion().compareTo(actual.getDescripcion()) > 0) {
                listaUtilEscolar.set(j+1, listaUtilEscolar.get(j));
                j--;
            }

            listaUtilEscolar.set(j+1, actual);
        }

        System.out.println("\nLista de pendientes ordenada alfabéticamente: ");

        for (UtilEscolar utilEscolar : listaUtilEscolar) {
            System.out.println(utilEscolar.obtenerDescripcionYPendiente());
        }
    }


    private static void imprimirLista() {
        if (listaUtilEscolar.isEmpty()) {
            System.out.println("La lista de pendientes está vacía.");
            return;
        }

        System.out.println("\nLista de pendientes:");

        for (UtilEscolar utilEscolar : listaUtilEscolar) {
            System.out.println(utilEscolar.obtenerDescripcionYPendiente());
        }
    }


}