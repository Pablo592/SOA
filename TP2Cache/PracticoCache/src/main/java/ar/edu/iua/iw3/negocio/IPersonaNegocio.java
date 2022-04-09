package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.persistencia.Persona;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

import java.util.List;

public interface IPersonaNegocio {

    List<Persona> listado() throws NegocioException;
    Persona cargar(long id) throws NegocioException, NoEncontradoException;
    Persona agregar(Persona persona) throws NegocioException;
    Persona modificar(Persona persona) throws NegocioException;
    void eliminar(long id) throws NegocioException, NoEncontradoException;
}
