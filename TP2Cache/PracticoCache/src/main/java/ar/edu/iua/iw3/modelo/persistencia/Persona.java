package ar.edu.iua.iw3.modelo.persistencia;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Personas")
public class Persona implements Serializable {

    public Persona(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getJson(){
        String json = "{\"id\":"+this.id+",\"nombre\": \""+this.nombre+"\",\"apellido\": \""+this.apellido+"\"}";
        return json;
    }

}
