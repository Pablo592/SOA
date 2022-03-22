package ar.edu.iua.iw3.Business;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.iw3.DAO.OrderRepository;
import ar.edu.iua.iw3.Model.Order;
import ar.edu.iua.iw3.negocio.excepciones.BusinessException;
import ar.edu.iua.iw3.negocio.excepciones.NotFoundException;


@Service
public class OrderBusiness implements IorderBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderRepository OrderDAO;

	
	@Override
	public Order add(Order Order) throws BusinessException {
		try {
			return OrderDAO.save(Order);
		} catch (Exception e) {
			throw new BusinessException(e);
		}	
	}
	
	@Override
	public Order loadById(long id_Order) throws NotFoundException, BusinessException {
		Optional<Order> op;
		try {
			op = OrderDAO.findById(id_Order);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("La Order con el id " + id_Order + " no se encuentra en la BD");
		}
		return op.get();
	}

		

}