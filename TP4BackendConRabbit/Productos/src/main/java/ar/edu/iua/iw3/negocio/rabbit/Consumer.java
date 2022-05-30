package ar.edu.iua.iw3.negocio.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.iua.iw3.negocio.IProductoNegocio;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;

import java.time.LocalDateTime;

@Component
public class Consumer {
	@Autowired
    private IProductoNegocio productoNegocio;
	
    @RabbitListener(queues = "rabbitQueue")
    public void receive(String message) throws NegocioException,NoEncontradoException  {
    	productoNegocio.discountStock(message);
		System.out.println("Message " + message + "  " + LocalDateTime.now());
    	
    }
    
}
