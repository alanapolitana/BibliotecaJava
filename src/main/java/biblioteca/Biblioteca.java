package biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private List<Usuario> usuarios;
    private List<Libro> libros;
    private List<Alquiler> alquileres;

    public Biblioteca() {
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
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombre() + "!");
                return usuario;
            }
        }

        System.out.println("Credenciales incorrectas. Por favor, intente nuevamente.");
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

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

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
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    verCatalogoLibros();
                    break;
                case 2:
                    alquilarLibro(usuario);
                    break;
                case 3:
                    devolverLibro(usuario);
                    break;
                case 4:
                    verLibrosAlquilados(usuario);
                    break;
                    
                case 5:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
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

public void devolverLibro(Usuario usuario) {
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
            alquiler.setDevuelto(true);
            alquiler.getLibro().setDisponible(true);
            System.out.println("Libro devuelto con éxito.");
            return;
        }
    }

    System.out.println("Libro no encontrado en su lista de libros alquilados o ya ha sido devuelto.");
}

    public void verLibrosAlquilados(Usuario usuario) {
        System.out.println("\n--- Libros Alquilados ---");
        for (Alquiler alquiler : usuario.getLibrosAlquilados()) {
            String estadoDevuelto = alquiler.isDevuelto() ? " - Estado: Devuelto" : " - Estado: No devuelto";
            System.out.println(alquiler.getLibro().getTitulo() + " - Fecha de devolución: " + alquiler.getFechaDevolucion() + estadoDevuelto);
        }
    }
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        
        Usuario usuario1 = new Usuario(1, "Lionel Messi", "messi@gmail.com", "messi123", new ArrayList<>());
        Usuario usuario2 = new Usuario(2, "Diego Maradona", "maradona@gmail.com", "maradona123", new ArrayList<>());
        Usuario usuario3 = new Usuario(3, "Carlos Tevez", "tevez@gmail.com", "tevez123", new ArrayList<>());
        Usuario usuario4 = new Usuario(4, "Luis Suárez", "suarez@gmail.com", "suarez123", new ArrayList<>());
        Usuario usuario5 = new Usuario(5, "Juan Román Riquelme", "riquelme@gmail.com", "riquelme123", new ArrayList<>());

        
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

        
        Alquiler alquiler1 = new Alquiler(usuario1, libro1);
        Alquiler alquiler2 = new Alquiler(usuario2, libro6);

       
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);
        biblioteca.registrarUsuario(usuario3);
        biblioteca.registrarUsuario(usuario4);
        biblioteca.registrarUsuario(usuario5);

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

        biblioteca.alquileres.add(alquiler1);
        biblioteca.alquileres.add(alquiler2);
     
        biblioteca.menuPrincipal();
    }
}
