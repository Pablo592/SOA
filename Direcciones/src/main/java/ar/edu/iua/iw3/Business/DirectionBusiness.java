package ar.edu.iua.iw3.Business;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.iw3.DAO.DirectionRepository;
import ar.edu.iua.iw3.Model.Direction;
import ar.edu.iua.iw3.negocio.excepciones.BusinessException;
import ar.edu.iua.iw3.negocio.excepciones.NotFoundException;


@Service
public class DirectionBusiness implements IdirectionBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DirectionRepository DirectionDAO;

	
	@Override
	public Direction add(Direction direction) throws BusinessException {
		try {
			return DirectionDAO.save(direction);
		} catch (Exception e) {
			throw new BusinessException(e);
		}	
	}
	
	@Override
	public Direction loadById(Long id_Direction) throws NotFoundException, BusinessException {
		Optional<Direction> op;
		try {
			op = DirectionDAO.findById(id_Direction);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("La Direction con el id " + id_Direction + " no se encuentra en la BD");
		}
		return op.get();
	}

		

}