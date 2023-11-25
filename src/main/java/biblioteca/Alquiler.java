package biblioteca;

import java.time.LocalDate;

public class Alquiler {
    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;
    private boolean devuelto;

    // Constructor
    public Alquiler(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaAlquiler = LocalDate.now();
        this.fechaDevolucion = null;
        this.devuelto = false;
    }

    // Getters y setters (puedes generarlos automáticamente en tu IDE)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}
