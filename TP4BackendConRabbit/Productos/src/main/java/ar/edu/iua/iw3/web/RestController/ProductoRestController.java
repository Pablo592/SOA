package ar.edu.iua.iw3.web.RestController;

import ar.edu.iua.iw3.modelo.DTO.StockDTO;
import ar.edu.iua.iw3.modelo.persistencia.Producto;
import ar.edu.iua.iw3.negocio.IProductoNegocio;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.amqp.core.AmqpTemplate;
import java.util.List;

@RestController
public class ProductoRestController {

    private Logger log = LoggerFactory.getLogger(ProductoRestController.class);


    @Autowired
    private IProductoNegocio productoNegocio;
    
    @Autowired
    private AmqpTemplate queueSender ;

    @GetMapping(value= "/productos/{id}/{cantidad}")
    public HttpEntity<? extends Object> buscarUsuario(@PathVariable("id") long id, @PathVariable("cantidad") int cantidad) {
        try {
            return new ResponseEntity<StockDTO>(productoNegocio.existenciaStock(id, cantidad), HttpStatus.OK);
        } catch (NegocioException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoEncontradoException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

        @GetMapping(value= "/productos/search/{letras}")
        public HttpEntity<? extends Object> search(@PathVariable("letras") String letras) {
            try {
                return new ResponseEntity<List<Producto>>(productoNegocio.listarProductoTitulo(letras), HttpStatus.OK);
            } catch (NegocioException e) {
                log.error(e.getMessage(), e);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (NoEncontradoException e) {
                log.error(e.getMessage(), e);
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            }

    }

    @GetMapping(value= "/productos/search/")
    public HttpEntity<? extends Object> searchAll() {
        try {
            return new ResponseEntity<List<Producto>>(productoNegocio.cargarTodo(), HttpStatus.OK);
        } catch (NegocioException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
   
    @GetMapping("/test/{id}")
    public String send(@PathVariable("id") long id){

      String mensage = String.valueOf(id);

      queueSender.convertAndSend("rabbitQueue", mensage); //exc -
      return "ok. done";
  }
}
