package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int ID_usuario;
    private String nombre;
    private String correo;
    private String contraseña;
    private List<Alquiler> librosAlquilados;

 
    public Usuario(int ID_usuario, String nombre, String correo, String contraseña, List<Alquiler> librosAlquilados) {
        this.ID_usuario = ID_usuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.librosAlquilados = librosAlquilados;
    }

    public int getID_usuario() {
        return ID_usuario;
    }

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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Alquiler> getLibrosAlquilados() {
        return librosAlquilados;
    }

    public void setLibrosAlquilados(List<Alquiler> librosAlquilados) {
        this.librosAlquilados = librosAlquilados;
    }

    public void agregarAlquiler(Alquiler alquiler) {
        librosAlquilados.add(alquiler);
    }

    public void eliminarAlquiler(Alquiler alquiler) {
        librosAlquilados.remove(alquiler);
    }
}
