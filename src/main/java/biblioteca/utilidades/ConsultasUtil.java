package biblioteca.utilidades;

import biblioteca.Libro;
import biblioteca.Usuario;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsultasUtil {

    public static List<Libro> obtenerLibrosPorAutor(List<Libro> libros, String autor) {
        return libros.stream()
                .filter(libro -> libro.getAutor().equals(autor))
                .collect(Collectors.toList());
    }

    public static Map<Usuario, List<Libro>> generarInformeUsuarios(List<Usuario> usuarios) {
        return usuarios.stream()
                .collect(Collectors.toMap(
                        usuario -> usuario,
                        usuario -> usuario.getLibrosAlquilados()
                ));
    }
}
