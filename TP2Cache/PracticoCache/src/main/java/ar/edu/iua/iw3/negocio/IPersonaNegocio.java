package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.persistencia.Persona;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IPersonaNegocio {

    List<Persona> listado() throws NegocioException;
    Persona cargar(long id) throws NegocioException, NoEncontradoException, JsonProcessingException;
    Persona agregar(Persona persona) throws NegocioException;
    Persona modificar(Persona persona) throws NegocioException;
    void eliminar(long id) throws NegocioException, NoEncontradoException, JsonProcessingException;
}
