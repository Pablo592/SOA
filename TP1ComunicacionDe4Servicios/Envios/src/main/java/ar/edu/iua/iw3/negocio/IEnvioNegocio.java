package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.persistencia.Envio;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

import java.util.List;

public interface IEnvioNegocio {
    List<Envio> cargar(long id) throws NegocioException, NoEncontradoException;
}
