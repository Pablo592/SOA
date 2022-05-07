package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.DTO.StockDTO;
import ar.edu.iua.iw3.modelo.persistencia.Producto;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

import java.util.List;

public interface IProductoNegocio {
    StockDTO existenciaStock(long id, int cantidad) throws NegocioException, NoEncontradoException;
    List<Producto> listarProductoTitulo(String letras) throws NegocioException, NoEncontradoException;
    List<Producto> cargarTodo() throws NegocioException;
}
