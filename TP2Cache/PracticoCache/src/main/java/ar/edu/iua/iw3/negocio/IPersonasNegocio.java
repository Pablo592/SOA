package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.persistencia.Personas;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

import java.util.List;

public interface IPersonasNegocio {

    public List<Personas> listado() throws NegocioException;
    Personas cargar(long id) throws NegocioException, NoEncontradoException;
    Personas agregar(Personas personas) throws NegocioException;
    Personas modificar(Personas personas) throws NegocioException;
    public void eliminar(long id) throws NegocioException, NoEncontradoException;
}
