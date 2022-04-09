package ar.edu.iua.iw3.modelo.DTO;

import ar.edu.iua.iw3.modelo.persistencia.Usuario;

import java.util.List;

public class UsuarioDatosDTO {


    public UsuarioDatosDTO(Usuario user, List<Object> direcciones, int cantidadOrdenes, boolean enviosPendientes){
        this.user = user;
        this.direcciones = direcciones;
        this.cantidadOrdenes = cantidadOrdenes;
        this.enviosPendientes = enviosPendientes;
    }


    private Usuario user;
    private List<Object> direcciones;
    private int cantidadOrdenes;
    private boolean enviosPendientes;


    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Object> getdirecciones() {
        return direcciones;
    }

    public void setdirecciones(List<Object> direcciones) {
        this.direcciones = direcciones;
    }

    public int getCantidadOrdenes() {
        return cantidadOrdenes;
    }

    public void setCantidadOrdenes(int cantidadOrdenes) {
        this.cantidadOrdenes = cantidadOrdenes;
    }

    public boolean isEnviosPendientes() {
        return enviosPendientes;
    }

    public void setEnviosPendientes(boolean enviosPendientes) {
        this.enviosPendientes = enviosPendientes;
    }
}
