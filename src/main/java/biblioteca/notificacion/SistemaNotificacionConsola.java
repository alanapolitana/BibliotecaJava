package biblioteca.notificacion;

public class SistemaNotificacionConsola implements SistemaNotificacion {
    @Override
    public void notificarUsuario(String mensaje) {
        System.out.println("Notificación: " + mensaje);
    }
}
