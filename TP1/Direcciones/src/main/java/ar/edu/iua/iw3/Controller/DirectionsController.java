package ar.edu.iua.iw3.Controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import ar.edu.iua.iw3.Business.IdirectionBusiness;
import ar.edu.iua.iw3.Model.Direction;
import ar.edu.iua.iw3.negocio.excepciones.BusinessException;
import ar.edu.iua.iw3.negocio.excepciones.NotFoundException;

@RestController
public class DirectionsController {


    private Logger log = LoggerFactory.getLogger(DirectionsController.class);


    @Autowired
    private IdirectionBusiness directionBusiness;

    @GetMapping(value= "/directions/{id}")
    public ResponseEntity<Direction> searchDirection(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Direction>(directionBusiness.loadById(id), HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Direction>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Direction>(HttpStatus.NOT_FOUND);
        }
    }
 
    @GetMapping(value= "/directions/find_by_user/{id_user}")
    public ResponseEntity<List<Direction>> searchDirectionByUserId(@PathVariable("id_user") long id_user) throws NotFoundException {
		try {
			log.debug("GetMapping: Una lista de orden detalle ");
			return new ResponseEntity<List<Direction>>(directionBusiness.loadByIdUser(id_user), HttpStatus.OK);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Direction>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PostMapping(value = "/directions/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Direction direction) {
		try {
			directionBusiness.add(direction);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "localhost:8082/directions" + '/' + direction.getId_direction()); //Constantes.URL_directionES 
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {

			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}