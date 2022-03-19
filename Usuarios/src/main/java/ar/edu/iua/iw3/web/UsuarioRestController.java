package ar.edu.iua.iw3.web;

import ar.edu.iua.iw3.modelo.DTO.UsuarioDatosDTO;
import ar.edu.iua.iw3.modelo.persistencia.Usuario;
import ar.edu.iua.iw3.negocio.IUsuarioNegocio;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioRestController {


    private Logger log = LoggerFactory.getLogger(UsuarioRestController.class);


    @Autowired
    private IUsuarioNegocio usuarioNegocio;

    @GetMapping(value= "/usuarios/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Usuario>(usuarioNegocio.cargar(id), HttpStatus.OK);
        } catch (NegocioException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value= "/usuarios-datos/{id}")
    public ResponseEntity<UsuarioDatosDTO> buscarUsuarioDatos(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<UsuarioDatosDTO>(usuarioNegocio.usuarioDatos(id), HttpStatus.OK);
        } catch (NegocioException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<UsuarioDatosDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<UsuarioDatosDTO>(HttpStatus.NOT_FOUND);
        }
    }
}
