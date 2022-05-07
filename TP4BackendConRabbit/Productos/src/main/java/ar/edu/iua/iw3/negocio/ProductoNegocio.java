package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.DTO.StockDTO;
import ar.edu.iua.iw3.modelo.persistencia.Producto;
import ar.edu.iua.iw3.modelo.repository.ProductoRepository;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
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

    private Logger log = LoggerFactory.getLogger(ProductoNegocio.class);


    @Override
    public StockDTO existenciaStock(long id, int cantidad) throws NegocioException, NoEncontradoException {
        Optional<Producto> o;
        try {
            o = productoDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        if (!o.isPresent()) {
            throw new NoEncontradoException("No se encuentra el Producto con id=" + id);
        }
        return (o.get().getStock() >= cantidad)? new StockDTO(true,o.get().getPrecio()) : new StockDTO(false,o.get().getPrecio());
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

}