package biblioteca.Utils;

import biblioteca.Alquiler;
import biblioteca.Libro;
import biblioteca.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultasUtils {

    public static List<Libro> obtenerLibrosDisponibles(List<Libro> libros) {
        List<Libro> librosDisponibles = libros.stream()
                .filter(Libro::isDisponible)
                .collect(Collectors.toList());

        if (librosDisponibles.isEmpty()) {
            System.out.println("No hay libros disponibles en este momento.");
        } else {
            System.out.println("Libros disponibles:");
            librosDisponibles.forEach(System.out::println);
        }

        return librosDisponibles;
    }

    public static List<Alquiler> obtenerAlquileresVencidos(List<Alquiler> alquileres) {
        LocalDate fechaActual = LocalDate.now();
        List<Alquiler> alquileresVencidos = alquileres.stream()
                .filter(alquiler -> !alquiler.isDevuelto() && fechaActual.isAfter(alquiler.getFechaDevolucion()))
                .collect(Collectors.toList());

        if (alquileresVencidos.isEmpty()) {
            System.out.println("No hay alquileres vencidos en este momento.");
        } else {
            System.out.println("Alquileres vencidos:");
            alquileresVencidos.forEach(System.out::println);
        }

        return alquileresVencidos;
    }
}





