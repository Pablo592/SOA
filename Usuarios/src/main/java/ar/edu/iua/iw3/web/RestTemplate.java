package ar.edu.iua.iw3.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestTemplate {

    private Logger log = LoggerFactory.getLogger(RestTemplate.class);


    @Autowired
    org.springframework.web.client.RestTemplate restTemplate;


    public Object getEnviosList(String id) {
        String url = "http://localhost:8081/envios/".concat(id);
        Object forObject = restTemplate.getForObject(url,Object.class);
        log.info("Result" + forObject);
        return forObject;
    }
    //EDIT AYEEE	
    public Object getDireccionesList(String id) {
        String url = "http://localhost:8082/directions/find_by_user/".concat(id);
        Object forObject = restTemplate.getForObject(url,Object.class);
        log.info("Result" + forObject);
        return forObject;
    }
    
    public Integer getOrdenersCount(String id) {
        String url = "http://localhost:8083/orders/orders_by_user/".concat(id);
        Integer forObject = (Integer) restTemplate.getForObject(url,Object.class);
        log.info("Result" + forObject);
        return forObject;
    }

}
