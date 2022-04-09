package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.DTO.UsuarioDatosDTO;
import ar.edu.iua.iw3.modelo.persistencia.Usuario;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

public interface IUsuarioNegocio {

    Usuario cargar(long id) throws NegocioException, NoEncontradoException;
    UsuarioDatosDTO usuarioDatos(long id) throws NegocioException, NoEncontradoException;
}
