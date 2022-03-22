package ar.edu.iua.iw3.Business;

import ar.edu.iua.iw3.Model.Order;
import ar.edu.iua.iw3.negocio.excepciones.BusinessException;
import ar.edu.iua.iw3.negocio.excepciones.NotFoundException;

public interface IorderBusiness {

	Order add(Order Order) throws BusinessException, BusinessException;

	Order loadById(long id_Order) throws NotFoundException, BusinessException;

	Integer countOrders(long id_User) throws NotFoundException, BusinessException;

	}
