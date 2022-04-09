package ar.edu.iua.iw3.web;

import ar.edu.iua.iw3.modelo.persistencia.Persona;
import ar.edu.iua.iw3.negocio.IPersonaNegocio;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@RestController
public class PersonaRestController {

    @Autowired
    private IPersonaNegocio personaNegocio;

    private Logger log = LoggerFactory.getLogger(PersonaRestController.class);


    @GetMapping(value="personas/listar")
    public ResponseEntity<List<Persona>> listado() {
        try {
            return new ResponseEntity<List<Persona>>(personaNegocio.listado(), HttpStatus.OK);
        } catch (NegocioException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<List<Persona>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value="personas/listar/{id}")
    public ResponseEntity<Persona> buscarUna(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Persona>(personaNegocio.cargar(id), HttpStatus.OK);
        } catch (NegocioException | NoEncontradoException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Persona>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping(value="personas/agregar")
    public ResponseEntity<String> agregar(@RequestBody Persona persona) {
        try {
            Persona respuesta= personaNegocio.agregar(persona);
            HttpHeaders responseHeaders=new HttpHeaders();
            responseHeaders.set("location", "/camiones/"+respuesta.getId());
            return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
        } catch (NegocioException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (HttpClientErrorException.BadRequest e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="personas/modificar")
    public ResponseEntity<String> modificar(@RequestBody Persona persona) {
        try {
            personaNegocio.modificar(persona);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NegocioException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value="personas/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") long id) {
        try {
            personaNegocio.eliminar(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NegocioException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}
