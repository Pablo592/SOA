package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.persistencia.Envio;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

public interface IEnvioNegocio {
    Envio cargar(long id) throws NegocioException, NoEncontradoException;
}
