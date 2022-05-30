package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.DTO.StockDTO;
import ar.edu.iua.iw3.modelo.persistencia.Orden;
import ar.edu.iua.iw3.modelo.persistencia.Producto;

import ar.edu.iua.iw3.modelo.repository.ProductoRepository;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

import ar.edu.iua.iw3.web.RestTemplate.RestTemplateOrden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoNegocio implements IProductoNegocio{


    @Autowired
    private ProductoRepository productoDAO;
    private RestTemplateOrden ordenService;

    private Logger log = LoggerFactory.getLogger(ProductoNegocio.class);


    @Override
    public StockDTO existenciaStock(long id, int cantidad) throws NegocioException, NoEncontradoException {
        Producto o= productById(id);
        return (o.getStock() >= cantidad)? new StockDTO(true,o.getPrecio()) : new StockDTO(false,o.getPrecio());
    }
    
    public Producto productById(long id) throws NegocioException, NoEncontradoException{
    	Optional<Producto> p;
    	try {
            p = productoDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        if (!p.isPresent()) {
            throw new NoEncontradoException("No se encuentra el Producto con id=" + id);
        }
        return p.get();
    }

    @Override
    public List<Producto> listarProductoTitulo(String letras) throws NegocioException, NoEncontradoException{
        Optional<List<Producto>> o = null;
            try {
                o = Optional.ofNullable(productoDAO.findAllByTituloContaining(letras));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new NegocioException(e);
            }
            if (!o.isPresent()) {
                throw new NoEncontradoException("No se encuentra el producto con el titulo con las letras " + letras);
            }
        return o.get();
    }


    @Override
    public List<Producto> cargarTodo() throws NegocioException{
        Optional<List<Producto>> o = null;
        try {
            o = Optional.ofNullable(productoDAO.findAll());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        return o.get();
    }
    
    @Override
    public void discountStock(String id) throws NegocioException, NoEncontradoException {
    	//Obtenemos el id de la orden
    	 Long orderId= Long.parseLong(id) ;
    	 
    	 //Buscamos la orden por Id
    	 Orden order= ordenService.getOrderById(orderId);
    	 
    	/*----Prueba Unitaria----*/
    	/*Orden order = new Orden();
     	order.setId(1);
     	order.setProductoId(1);
     	order.setCantidad(2);
     	System.out.println("orden:"+order.toString());*/
    	 
    	 //Buscamos el Producto
    	 Producto p=  productById(Long.valueOf(order.getProductoId()));
    	
    	 
    	 //Descontamos el Stock
    	 int stockViejo= p.getStock();
    	 int stockNuevo= stockViejo-order.getCantidad();
    	 p.setStock(stockNuevo);
    	 System.out.println(p.toString());
    	 try {
    		 productoDAO.save(p);
         } catch (Exception e) {
             log.error(e.getMessage(), e);
             throw new NegocioException(e);
         }  	
    	 
    }
    
    

}