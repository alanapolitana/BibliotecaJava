package biblioteca;

import biblioteca.excepciones.DevolucionFueraDePlazoException;
import biblioteca.notificacion.AbstractBiblioteca;
import biblioteca.notificacion.SistemaNotificacion;
import biblioteca.notificacion.SistemaNotificacionConsola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Alquiler> alquileres;
    private SistemaNotificacion sistemaNotificacion;

    public Biblioteca(List<Libro> libros, List<Usuario> usuarios, List<Alquiler> alquileres, SistemaNotificacion sistemaNotificacion) {
        this.libros = libros;
        this.usuarios = usuarios;
        this.alquileres = alquileres;
        this.sistemaNotificacion = sistemaNotificacion;
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro agregado: " + libro.getTitulo());
    }

    public void eliminarLibro(Libro libro) {
        libros.remove(libro);
        System.out.println("Libro eliminado: " + libro.getTitulo());
    }

    public void realizarAlquiler(Usuario usuario, Libro libro) {
        Alquiler alquiler = new Alquiler(usuario, libro);
        alquileres.add(alquiler);
        usuario.agregarAlquiler(alquiler);
        libro.setDisponible(false);

        sistemaNotificacion.notificarUsuario("Has alquilado el libro: " + libro.getTitulo());
    }

    public void devolverLibro(Alquiler alquiler) {
        alquiler.getLibro().setDisponible(true);
        alquileres.remove(alquiler);
        alquiler.getUsuario().eliminarAlquiler(alquiler);

        sistemaNotificacion.notificarUsuario("Has devuelto el libro: " + alquiler.getLibro().getTitulo());
    }

    public List<Libro> buscarLibros(String criterio, String valor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            switch (criterio.toLowerCase()) {
                case "titulo":
                    if (libro.getTitulo().toLowerCase().contains(valor.toLowerCase())) {
                        resultados.add(libro);
                    }
                    break;
                case "autor":
                    if (libro.getAutor().toLowerCase().contains(valor.toLowerCase())) {
                        resultados.add(libro);
                    }
                    break;
                case "genero":
                    if (libro.getGenero().toLowerCase().contains(valor.toLowerCase())) {
                        resultados.add(libro);
                    }
                    break;
                default:
                    System.out.println("Criterio de búsqueda no válido");
                    break;
            }
        }
        return resultados;
    }

    public void generarInforme() {
        System.out.println("Generando informe...");
        // Lógica para generar informes y estadísticas
    }

    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        Usuario usuarioActual = null;

        do {
            if (usuarioActual == null) {
                // Menú para usuario no registrado
                System.out.println("Menú Principal");
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar sesión");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        // Lógica para registrar nuevo usuario
                        System.out.println("Ingrese detalles para registrarse:");
                        System.out.print("Nombre: ");
                        String nombreRegistro = scanner.next();
                        System.out.print("Correo electrónico: ");
                        String correoRegistro = scanner.next();
                        usuarioActual = new Usuario(nombreRegistro, correoRegistro, new ArrayList<>());
                        usuarios.add(usuarioActual);
                        System.out.println("Registrado con éxito. Ahora puedes iniciar sesión.");
                        break;
                    case 2:
                        // Lógica para iniciar sesión
                        System.out.println("Ingrese detalles para iniciar sesión:");
                        System.out.print("Correo electrónico: ");
                        String correoInicioSesion = scanner.next();
                        usuarioActual = buscarUsuarioPorCorreo(correoInicioSesion);
                        if (usuarioActual != null) {
                            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuarioActual.getNombre() + "!");
                        } else {
                            System.out.println("Inicio de sesión fallido. Usuario no encontrado.");
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                // Menú para usuario registrado
                System.out.println("Menú de la Biblioteca");
                System.out.println("1. Gestionar Libros");
                System.out.println("2. Realizar Alquiler");
                System.out.println("3. Devolver Libro");
                System.out.println("4. Consultas y Búsquedas");
                System.out.println("5. Informes y Estadísticas");
                System.out.println("0. Cerrar sesión");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        gestionarLibros();
                        break;
                    case 2:
                        realizarAlquiler(usuarioActual);
                        break;
                    case 3:
                        devolverLibro(usuarioActual);
                        break;
                    case 4:
                        consultarLibros();
                        break;
                    case 5:
                        generarInforme();
                        break;
                    case 0:
                        System.out.println("Cerrando sesión de " + usuarioActual.getNombre() + "...");
                        usuarioActual = null;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } while (opcion != 0);
    }

    private Usuario buscarUsuarioPorCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                return usuario;
            }
        }
        return null;
    }

    private void gestionarLibros() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Gestión de Libros");
            System.out.println("1. Agregar libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para agregar libro
                    System.out.println("Ingrese detalles del libro:");
                    System.out.print("Título: ");
                    String titulo = scanner.next();
                    System.out.print("Autor: ");
                    String autor = scanner.next();
                    System.out.print("Género: ");
                    String genero = scanner.next();
                    Libro nuevoLibro = new Libro(titulo, autor, genero, true);
                    agregarLibro(nuevoLibro);
                    break;
                case 2:
                    // Lógica para eliminar libro
                    System.out.println("Seleccione el libro a eliminar:");
                    for (int i = 0; i < libros.size(); i++) {
                        System.out.println(i + 1 + ". " + libros.get(i).getTitulo());
                    }
                    int indiceEliminar = scanner.nextInt();
                    if (indiceEliminar >= 1 && indiceEliminar <= libros.size()) {
                        Libro libroEliminar = libros.get(indiceEliminar - 1);
                        eliminarLibro(libroEliminar);
                    } else {
                        System.out.println("Índice no válido. Intente de nuevo.");
                    }
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    private void realizarAlquiler(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el libro para alquiler:");
        for (int i = 0; i < libros.size(); i++) {
            System.out.println(i + 1 + ". " + libros.get(i).getTitulo());
        }
        int indiceLibro = scanner.nextInt();
        if (indiceLibro >= 1 && indiceLibro <= libros.size()) {
            Libro libroAlquiler = libros.get(indiceLibro - 1);
            realizarAlquiler(usuario, libroAlquiler);
        } else {
            System.out.println("Índice no válido. Intente de nuevo.");
        }
    }

    private void devolverLibro(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Libros alquilados por " + usuario.getNombre() + ":");
        List<Libro> librosAlquilados = usuario.getLibrosAlquilados();
        for (int i = 0; i < librosAlquilados.size(); i++) {
            System.out.println(i + 1 + ". " + librosAlquilados.get(i).getTitulo());
        }

        if (librosAlquilados.isEmpty()) {
            System.out.println("No tienes libros alquilados.");
            return;
        }

        System.out.println("Seleccione el libro para devolver:");
        int indiceLibroDevolver = scanner.nextInt();
        if (indiceLibroDevolver >= 1 && indiceLibroDevolver <= librosAlquilados.size()) {
            Libro libroDevolver = librosAlquilados.get(indiceLibroDevolver - 1);
            Alquiler alquilerDevolver = usuario.getAlquilerPorLibro(libroDevolver);
            devolverLibro(alquilerDevolver);
        } else {
            System.out.println("Índice no válido. Intente de nuevo.");
        }
    }

    private void consultarLibros() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menú de Consultas y Búsquedas");
        System.out.println("1. Buscar libros por título");
        System.out.println("2. Buscar libros por autor");
        System.out.println("3. Buscar libros por género");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                buscarLibrosPorCriterio("titulo");
                break;
            case 2:
                buscarLibrosPorCriterio("autor");
                break;
            case 3:
                buscarLibrosPorCriterio("genero");
                break;
            case 4:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private void buscarLibrosPorCriterio(String criterio) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el valor para la búsqueda: ");
        String valor = scanner.next();

        List<Libro> resultados = buscarLibros(criterio, valor);

        if (!resultados.isEmpty()) {
            System.out.println("Resultados de la búsqueda:");
            for (int i = 0; i < resultados.size(); i++) {
                System.out.println(i + 1 + ". " + resultados.get(i).getTitulo());
            }
        } else {
            System.out.println("No se encontraron resultados para la búsqueda.");
        }
    }

    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();
        List<Alquiler> alquileres = new ArrayList<>();

        Biblioteca biblioteca = new Biblioteca(libros, usuarios, alquileres, new SistemaNotificacionConsola());
        biblioteca.menuPrincipal();
    }
}
