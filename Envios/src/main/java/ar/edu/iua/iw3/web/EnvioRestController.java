package ar.edu.iua.iw3.web;

import ar.edu.iua.iw3.modelo.persistencia.Envio;
import ar.edu.iua.iw3.negocio.IEnvioNegocio;
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
public class EnvioRestController {

    private Logger log = LoggerFactory.getLogger(EnvioRestController.class);

    @Autowired
    private IEnvioNegocio envioNegocio;


    @GetMapping(value= "/envios/{id}")
    public ResponseEntity<Envio> buscarUsuario(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Envio>(envioNegocio.cargar(id), HttpStatus.OK);
        } catch (NegocioException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Envio>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Envio>(HttpStatus.NOT_FOUND);
        }
    }
}
