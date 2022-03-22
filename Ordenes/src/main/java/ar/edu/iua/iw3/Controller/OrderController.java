package ar.edu.iua.iw3.Controller;


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

import ar.edu.iua.iw3.Business.IorderBusiness;
import ar.edu.iua.iw3.Model.Order;
import ar.edu.iua.iw3.negocio.excepciones.BusinessException;
import ar.edu.iua.iw3.negocio.excepciones.NotFoundException;

@RestController
public class OrderController {


    private Logger log = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private IorderBusiness orderBusiness;

    @GetMapping(value= "/orders/{id}")
    public ResponseEntity<Order> searchOrder(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Order>(orderBusiness.loadById(id), HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PostMapping(value = "/orders/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Order order) {
		try {
			orderBusiness.add(order);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "localhost:8082/orders" + '/' + order.getId_order()); //Constantes.URL_directionES 
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {

			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}