package biblioteca;

public class Libro {
    private String titulo;
    private String autor;
    private String genero;
    private boolean disponible;

    // Constructor
    public Libro(String titulo, String autor, String genero, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponible = disponible;
    }

    // Getters y setters (puedes generarlos automáticamente en tu IDE)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
