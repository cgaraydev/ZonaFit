package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {

        var salir = false;
        var sc = new Scanner(System.in);

        IClienteDAO clienteDao = new ClienteDAO();

        while (!salir) {
            try {
                var opcion = mostrarMenu(sc);
                salir = ejecutarOpcion(sc, opcion, clienteDao);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }

    private static boolean ejecutarOpcion(Scanner sc, int opcion, IClienteDAO clienteDao) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                System.out.println("Listado de Clientes:");
                var clientes = clienteDao.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Ingrese el id del cliente a buscar: ");
                var id = Integer.parseInt(sc.nextLine());
                var cliente = new Cliente(id);
                var encontrado = clienteDao.buscarClientePorId(cliente);
                if (encontrado) System.out.println("Cliente encontrado: " + cliente);
                else System.out.println("Cliente no encontrado: " + id);
            }
            case 3 -> {
                System.out.print("Ingrese el nombre del cliente: ");
                var nombre = sc.nextLine();
                System.out.print("Ingrese el apellido del cliente: ");
                var apellido = sc.nextLine();
                System.out.print("Ingrese la membresia del cliente: ");
                var membresia = Integer.parseInt(sc.nextLine());
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDao.agregarCliente(cliente);
                if (agregado) System.out.println("Cliente agregado: " + cliente);
                else System.out.println("No se pudo agregar el cliente: " + cliente);
            }
            case 4 -> {
                System.out.print("Ingrese el id del cliente a modificar: ");
                var id = Integer.parseInt(sc.nextLine());
                System.out.printf("Nombre: ");
                var nombre = sc.nextLine();
                System.out.print("Apellido: ");
                var apellido = sc.nextLine();
                System.out.printf("Membresia: ");
                var membresia = Integer.parseInt(sc.nextLine());
                var cliente = new Cliente(id, nombre, apellido, membresia);
                var modificado = clienteDao.modificarCliente(cliente);
                if (modificado) System.out.println("Cliente modificado: " + cliente);
                else System.out.println("No se pudo modificar el cliente: " + cliente);
            }
            case 5 -> {
                System.out.print("Ingrese el id del cliente a eliminar: ");
                var id = Integer.parseInt(sc.nextLine());
                var cliente = new Cliente(id);
                var eliminado = clienteDao.eliminarCliente(cliente);
                if (eliminado) System.out.println("Cliente eliminado: " + cliente);
                else System.out.println("No se pudo eliminar el cliente: " + cliente);
            }
            case 6 -> {
                System.out.println("Adios!");
                salir = true;
            }
            default -> System.out.println("Opcion no valida");
        }
        return salir;
    }

    private static int mostrarMenu(Scanner sc) {
        System.out.print("""
                Bienvenido a Zona Fit
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elija una opcion: \s""");
        return Integer.parseInt(sc.nextLine());
    }
}
