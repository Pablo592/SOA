package ar.edu.iua.iw3.Business;

import ar.edu.iua.iw3.Model.Direction;
import ar.edu.iua.iw3.negocio.excepciones.BusinessException;
import ar.edu.iua.iw3.negocio.excepciones.NotFoundException;

public interface IdirectionBusiness {

	Direction add(Direction Direction) throws BusinessException, BusinessException;

	Direction loadById(Long id_Direction) throws NotFoundException, BusinessException;

	}
