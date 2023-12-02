package biblioteca;

import biblioteca.notificacion.AbstractBiblioteca;
import biblioteca.Utils.ConsultasUtils;
import biblioteca.excepciones.LibroNoEncontradoException;
import biblioteca.excepciones.DevolucionFueraDePlazoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;

public class Biblioteca extends AbstractBiblioteca {

    private List<Usuario> usuarios;
    private List<Libro> libros;
    private List<Alquiler> alquileres;

    public Biblioteca(String nombreBiblioteca) {
        super(nombreBiblioteca);
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
        this.alquileres = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Usuario login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su correo electrónico:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseña = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContraseña().equals(contraseña)) {
                notificarUsuario("Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombre() + "!");
                return usuario;
            }
        }

        notificarUsuario("Credenciales incorrectas. Por favor, intente nuevamente.");
        return null;
    }

   public void menuPrincipal() {
    Scanner scanner = new Scanner(System.in);
    Usuario usuario = null;

    while (usuario == null) {
        System.out.println("\n--- Biblioteca - Menú Principal ---");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    usuario = registrarNuevoUsuario();
                    break;
                case 2:
                    usuario = login();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un número entero válido. Por favor, vuelva a intentarlo.");
            scanner.nextLine(); // Limpiar el buffer de entrada para evitar un bucle infinito
        }
    }

    menuBiblioteca(usuario);
}

    public Usuario registrarNuevoUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Registro de Nuevo Usuario ---");
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su correo electrónico:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseña = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario(usuarios.size() + 1, nombre, correo, contraseña, new ArrayList<>());
        usuarios.add(nuevoUsuario);

        System.out.println("Registro exitoso. ¡Bienvenido, " + nuevoUsuario.getNombre() + "!");
        return nuevoUsuario;
    }

    public void menuBiblioteca(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Biblioteca - Menú de Usuario ---");
            System.out.println("1. Ver catálogo de libros");
            System.out.println("2. Alquilar libro");
            System.out.println("3. Devolver libro");
            System.out.println("4. Ver libros alquilados");
            System.out.println("5. Administrar libros"); 
            System.out.println("6. Consultas Alquileres ");
            System.out.println("7. Busqueda por Genero Autor o Titulo");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    verCatalogoLibros();
                    break;
                case 2:
                    alquilarLibro(usuario);
                    break;
                case 3:
                try {
                    devolverLibro(usuario);
                } catch (DevolucionFueraDePlazoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
                case 4:
                    verLibrosAlquilados(usuario);
                    break;
                case 5:
                    menuAdministrarLibros(usuario); 
                    break;
                case 6:
                    realizarConsultas();
                    break;
                case 7:
                    menuConsultas();
                    break;
                case 8:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    System.exit(0);
               
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
public void realizarConsultas() {
    while (true) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Consultas ---");
        System.out.println("1. Ver libros disponibles");
        System.out.println("2. Ver alquileres vencidos");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1:
                ConsultasUtils.obtenerLibrosDisponibles(libros); 
                break;
            case 2:
                ConsultasUtils.obtenerAlquileresVencidos(alquileres);
                break;
            case 3:
                return;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
        }
    }
}

public void menuConsultas() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n--- Consultas ---");
        System.out.println("1. Búsqueda de libro por título");
        System.out.println("2. Búsqueda de libro por autor");
        System.out.println("3. Búsqueda de libro por género");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        switch (opcion) {
           
            case 1:
                buscarLibroPorTitulo();
                break;
            case 2:
                buscarLibroPorAutor();
                break;
            case 3:
                buscarLibroPorGenero();
                break;
            case 4:
                return;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
        }
    }
}
    public void buscarLibroPorTitulo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Búsqueda de Libro por Título ---");
        System.out.println("Ingrese el título del libro:");
        String tituloLibro = scanner.nextLine();

        try {
            Libro libroEncontrado = buscarLibroPorTitulo(tituloLibro);
            System.out.println("Libro encontrado: " + libroEncontrado);
        } catch (LibroNoEncontradoException e) {
            System.out.println("Libro no encontrado: " + e.getMessage());
        }
    }

    public void buscarLibroPorAutor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Búsqueda de Libro por Autor ---");
        System.out.println("Ingrese el autor del libro:");
        String autorLibro = scanner.nextLine();

        try {
            List<Libro> librosEncontrados = buscarLibroPorAutor(autorLibro);
            if (!librosEncontrados.isEmpty()) {
                System.out.println("Libros encontrados:");
                for (Libro libro : librosEncontrados) {
                    System.out.println(libro);
                }
            } else {
                System.out.println("No se encontraron libros del autor especificado.");
            }
        } catch (LibroNoEncontradoException e) {
            System.out.println("Libros no encontrados: " + e.getMessage());
        }
    }

    public void buscarLibroPorGenero() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Búsqueda de Libro por Género ---");
        System.out.println("Ingrese el género del libro:");
        String generoLibro = scanner.nextLine();

        try {
            List<Libro> librosEncontrados = buscarLibroPorGenero(generoLibro);
            if (!librosEncontrados.isEmpty()) {
                System.out.println("Libros encontrados:");
                for (Libro libro : librosEncontrados) {
                    System.out.println(libro);
                }
            } else {
                System.out.println("No se encontraron libros del género especificado.");
            }
        } catch (LibroNoEncontradoException e) {
            System.out.println("Libros no encontrados: " + e.getMessage());
        }
    }

  
    private Libro buscarLibroPorTitulo(String titulo) throws LibroNoEncontradoException {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        throw new LibroNoEncontradoException("Libro con título '" + titulo + "' no encontrado.");
    }

    private List<Libro> buscarLibroPorAutor(String autor) throws LibroNoEncontradoException {
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                librosEncontrados.add(libro);
            }
        }
        if (librosEncontrados.isEmpty()) {
            throw new LibroNoEncontradoException("Libros del autor '" + autor + "' no encontrados.");
        }
        return librosEncontrados;
    }

    private List<Libro> buscarLibroPorGenero(String genero) throws LibroNoEncontradoException {
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                librosEncontrados.add(libro);
            }
        }
        if (librosEncontrados.isEmpty()) {
            throw new LibroNoEncontradoException("Libros del género '" + genero + "' no encontrados.");
        }
        return librosEncontrados;
    }


    public void verLibrosDisponibles() {
        System.out.println("\n--- Libros Disponibles ---");
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getGenero());
            }
        }
    }

  

public void verAlquileresVencidos() {
    System.out.println("\n--- Alquileres Vencidos ---");
    LocalDate fechaActual = LocalDate.now();
    for (Alquiler alquiler : alquileres) {
        if (!alquiler.isDevuelto() && alquiler.getFechaDevolucion().isBefore(fechaActual)) {
            System.out.println("Usuario: " + alquiler.getUsuario().getNombre() +
                    " - Libro: " + alquiler.getLibro().getTitulo() +
                    " - Fecha de devolución: " + alquiler.getFechaDevolucion());
        }
    }
}
    public void verCatalogoLibros() {
        System.out.println("\n--- Catálogo de Libros ---");
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getGenero());
            }
        }
    }

    public void alquilarLibro(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Alquiler de Libro ---");
        verCatalogoLibros();
        System.out.println("Ingrese el título del libro que desea alquilar:");
        String tituloLibro = scanner.nextLine();

        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(tituloLibro) && libro.isDisponible()) {
                Alquiler nuevoAlquiler = new Alquiler(usuario, libro);
                alquileres.add(nuevoAlquiler);
                usuario.agregarAlquiler(nuevoAlquiler);
                libro.setDisponible(false);
                System.out.println("Libro alquilado con éxito. Fecha de devolución: " + nuevoAlquiler.getFechaDevolucion());
                return;
            }
        }

        System.out.println("Libro no disponible o no encontrado. Por favor, seleccione otro libro.");
    }

  public void devolverLibro(Usuario usuario) throws DevolucionFueraDePlazoException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\n--- Devolución de Libro ---");
    System.out.println("Libros alquilados:");
    for (Alquiler alquiler : usuario.getLibrosAlquilados()) {
        String estadoDevolucion = alquiler.isDevuelto() ? "Devuelto" : "No devuelto";
        System.out.println(alquiler.getLibro().getTitulo() + " - Fecha de devolución: " + alquiler.getFechaDevolucion() +
                " - Estado: " + estadoDevolucion);
    }
    System.out.println("Ingrese el título del libro que desea devolver:");
    String tituloLibro = scanner.nextLine();

    for (Alquiler alquiler : usuario.getLibrosAlquilados()) {
        if (alquiler.getLibro().getTitulo().equalsIgnoreCase(tituloLibro) && !alquiler.isDevuelto()) {
            if (esDevolucionFueraDePlazo(alquiler)) {
                throw new DevolucionFueraDePlazoException("Devolución fuera de plazo. Se han aplicado cargos adicionales.");
            }
            alquiler.setDevuelto(true);
            alquiler.getLibro().setDisponible(true);
            System.out.println("Libro devuelto con éxito.");
            return;
        }
    }

    System.out.println("Libro no encontrado en su lista de libros alquilados o ya ha sido devuelto.");
}

private boolean esDevolucionFueraDePlazo(Alquiler alquiler) {
    LocalDate fechaActual = LocalDate.now();
    return !alquiler.isDevuelto() && alquiler.getFechaDevolucion().isBefore(fechaActual);
}



    public void verLibrosAlquilados(Usuario usuario) {
        System.out.println("\n--- Libros Alquilados ---");
        for (Alquiler alquiler : usuario.getLibrosAlquilados()) {
            String estadoDevuelto = alquiler.isDevuelto() ? " - Estado: Devuelto" : " - Estado: No devuelto";
            System.out.println(alquiler.getLibro().getTitulo() + " - Fecha de devolución: " + alquiler.getFechaDevolucion() + estadoDevuelto);
        }
    }

    public void menuAdministrarLibros(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Administrar Libros ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Volver al menú principal");

            System.out.print("Seleccione una opción:: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    agregarLibro(usuario);
                    break;
                case 2:
                    eliminarLibro(usuario);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    public void agregarLibro(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Agregar Libro ---");
        System.out.println("Ingrese el título del libro:");
        String tituloLibro = scanner.nextLine();
        System.out.println("Ingrese el autor del libro:");
        String autorLibro = scanner.nextLine();
        System.out.println("Ingrese el género del libro:");
        String generoLibro = scanner.nextLine();

        Libro nuevoLibro = new Libro(tituloLibro, autorLibro, generoLibro, true);
        libros.add(nuevoLibro);

        System.out.println("Libro agregado con éxito al catálogo.");
    }

    public void eliminarLibro(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Eliminar Libro ---");
        verCatalogoLibros();

        System.out.println("Ingrese el título del libro que desea eliminar:");
        String tituloLibro = scanner.nextLine();

        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(tituloLibro)) {
                libros.remove(libro);
                System.out.println("Libro eliminado con éxito del catálogo.");
                return;
            }
        }

        System.out.println("Libro no encontrado en el catálogo.");
    }
public static void main(String[] args) {
    Biblioteca biblioteca = new Biblioteca("Biblioteca Ejemplo");

    Usuario usuario1 = new Usuario(1, "Lionel Messi", "messi@gmail.com", "messi123", new ArrayList<>());
    Usuario usuario2 = new Usuario(2, "Diego Maradona", "maradona@gmail.com", "maradona123", new ArrayList<>());
    Usuario usuario3 = new Usuario(3, "Carlos Tevez", "tevez@gmail.com", "tevez123", new ArrayList<>());
    Usuario usuario4 = new Usuario(4, "Luis Suárez", "suarez@gmail.com", "suarez123", new ArrayList<>());
    Usuario usuario5 = new Usuario(5, "Juan Román Riquelme", "riquelme@gmail.com", "riquelme123", new ArrayList<>());
    Usuario usuario6 = new Usuario(6, "Gabriel Batistuta", "batistuta@gmail.com", "batistuta123", new ArrayList<>());
    Usuario usuario7 = new Usuario(7, "Javier Zanetti", "zanetti@gmail.com", "zanetti123", new ArrayList<>());
    Usuario usuario8 = new Usuario(8, "Ariel Ortega", "ortega@gmail.com", "ortega123", new ArrayList<>());
    Usuario usuario9 = new Usuario(9, "Hernan Crespo", "crespo@gmail.com", "crespo123", new ArrayList<>());
    Usuario usuario10 = new Usuario(10, "Pablo Aimar", "aimar@gmail.com", "aimar123", new ArrayList<>());

    Libro libro1 = new Libro("Spider-Man: No Way Home", "Various", "Action", true);
    Libro libro2 = new Libro("Eternals", "Various", "Adventure", true);
    Libro libro3 = new Libro("Shang-Chi and the Legend of the Ten Rings", "Various", "Fantasy", true);
    Libro libro4 = new Libro("Black Widow", "Various", "Action", true);
    Libro libro5 = new Libro("Doctor Strange in the Multiverse of Madness", "Various", "Adventure", true);
    Libro libro6 = new Libro("To Kill a Mockingbird", "Harper Lee", "Fiction", true);
    Libro libro7 = new Libro("1984", "George Orwell", "Dystopian", true);
    Libro libro8 = new Libro("Pride and Prejudice", "Jane Austen", "Romance", true);
    Libro libro9 = new Libro("The Great Gatsby", "F. Scott Fitzgerald", "Classic", true);
    Libro libro10 = new Libro("The Catcher in the Rye", "J.D. Salinger", "Coming-of-age", true);
    Libro libro11 = new Libro("Don Segundo Sombra", "Ricardo Güiraldes", "Gaucho literature", true);
    Libro libro12 = new Libro("Martín Fierro", "José Hernández", "Epic poetry", true);
    Libro libro13 = new Libro("Santa Evita", "Tomás Eloy Martínez", "Historical fiction", true);
    Libro libro14 = new Libro("Hopscotch", "Julio Cortázar", "Experimental fiction", true);
    Libro libro15 = new Libro("The Invention of Morel", "Adolfo Bioy Casares", "Science fiction", true);
    Libro libro16 = new Libro("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", true);
    Libro libro17 = new Libro("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", true);
    Libro libro18 = new Libro("The Da Vinci Code", "Dan Brown", "Mystery", true);
    Libro libro19 = new Libro("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Science fiction", true);
    Libro libro20 = new Libro("The Hunger Games", "Suzanne Collins", "Dystopian", true);
    Libro libro21 = new Libro("The Shining", "Stephen King", "Horror", true);
    Libro libro22 = new Libro("Brave New World", "Aldous Huxley", "Science fiction", true);
    Libro libro23 = new Libro("The Martian", "Andy Weir", "Science fiction", true);
    Libro libro24 = new Libro("The Great Expectations", "Charles Dickens", "Classic", true);
    Libro libro25 = new Libro("The Chronicles of Narnia", "C.S. Lewis", "Fantasy", true);

    biblioteca.registrarUsuario(usuario1);
    biblioteca.registrarUsuario(usuario2);
    biblioteca.registrarUsuario(usuario3);
    biblioteca.registrarUsuario(usuario4);
    biblioteca.registrarUsuario(usuario5);
    biblioteca.registrarUsuario(usuario6);
    biblioteca.registrarUsuario(usuario7);
    biblioteca.registrarUsuario(usuario8);
    biblioteca.registrarUsuario(usuario9);
    biblioteca.registrarUsuario(usuario10);

    biblioteca.agregarLibro(libro1);
    biblioteca.agregarLibro(libro2);
    biblioteca.agregarLibro(libro3);
    biblioteca.agregarLibro(libro4);
    biblioteca.agregarLibro(libro5);
    biblioteca.agregarLibro(libro6);
    biblioteca.agregarLibro(libro7);
    biblioteca.agregarLibro(libro8);
    biblioteca.agregarLibro(libro9);
    biblioteca.agregarLibro(libro10);
    biblioteca.agregarLibro(libro11);
    biblioteca.agregarLibro(libro12);
    biblioteca.agregarLibro(libro13);
    biblioteca.agregarLibro(libro14);
    biblioteca.agregarLibro(libro15);
    biblioteca.agregarLibro(libro16);
    biblioteca.agregarLibro(libro17);
    biblioteca.agregarLibro(libro18);
    biblioteca.agregarLibro(libro19);
    biblioteca.agregarLibro(libro20);
    biblioteca.agregarLibro(libro21);
    biblioteca.agregarLibro(libro22);
    biblioteca.agregarLibro(libro23);
    biblioteca.agregarLibro(libro24);
    biblioteca.agregarLibro(libro25);

    biblioteca.menuPrincipal();
}
}