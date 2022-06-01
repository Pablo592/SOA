package ar.edu.iua.iw3.web.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.iw3.modelo.persistencia.Orden;


@RestController
public class RestTemplateOrden {

    private Logger log = LoggerFactory.getLogger(RestTemplateOrden.class);


    @Autowired
    org.springframework.web.client.RestTemplate restTemplate;
    
    public Orden getOrderById(Long id) {
        String url = "http://ec2-34-229-173-81.compute-1.amazonaws.com:8080/api/ordenes/".concat(String.valueOf(id));
        Orden forObject = restTemplate.getForObject(url, Orden.class);
        return forObject;
    }
}
