package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String correo;
    private List<Alquiler> alquileres;

    // Constructor
    public Usuario(String nombre, String correo, List<Alquiler> alquileres) {
        this.nombre = nombre;
        this.correo = correo;
        this.alquileres = alquileres;
    }

    // Getters y setters (puedes generarlos automáticamente en tu IDE)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }

    public void agregarAlquiler(Alquiler alquiler) {
        alquileres.add(alquiler);
    }

    public void eliminarAlquiler(Alquiler alquiler) {
        alquileres.remove(alquiler);
    }

    // Métodos faltantes
    public List<Libro> getLibrosAlquilados() {
        // Puedes implementar esta lógica según sea necesario
        return new ArrayList<>();
    }

    public Alquiler getAlquilerPorLibro(Libro libro) {
        // Puedes implementar esta lógica según sea necesario
        return null;
    }
}
