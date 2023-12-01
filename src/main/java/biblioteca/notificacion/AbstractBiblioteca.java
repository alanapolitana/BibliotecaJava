package biblioteca.notificacion;

public abstract class AbstractBiblioteca implements SistemaNotificacion {

    protected String nombreBiblioteca;

    public AbstractBiblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
    }

    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    @Override
    public void notificarUsuario(String mensaje) {
        System.out.println("Notificación de la biblioteca '" + getNombreBiblioteca() + "': " + mensaje);
    }
}
