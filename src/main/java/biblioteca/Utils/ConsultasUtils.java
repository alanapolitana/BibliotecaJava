package biblioteca.Utils;

import biblioteca.Alquiler;
import biblioteca.Libro;
import biblioteca.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultasUtils {

    public static List<Libro> obtenerLibrosDisponibles(List<Libro> libros) {
        return libros.stream()
                .filter(Libro::isDisponible)
                .collect(Collectors.toList());
    }

    public static List<Alquiler> obtenerAlquileresVencidos(List<Alquiler> alquileres) {
        LocalDate fechaActual = LocalDate.now();
        return alquileres.stream()
                .filter(alquiler -> !alquiler.isDevuelto() && fechaActual.isAfter(alquiler.getFechaDevolucion()))
                .collect(Collectors.toList());
    }





}
