package ar.edu.iua.iw3.modelo.persistencia;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Envio  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long orden_id;

    private long user;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status{pendiente, enProceso,entregado}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public Long getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(Long orden_id) {
        this.orden_id = orden_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrden_id(long orden_id) {
        this.orden_id = orden_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
