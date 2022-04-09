package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.persistencia.Personas;
import ar.edu.iua.iw3.modelo.repository.PersonasRepository;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonasNegocio implements IPersonasNegocio{

    private Logger log = LoggerFactory.getLogger(PersonasNegocio.class);

    @Autowired
    private PersonasRepository personasDAO;

    @Override
    public List<Personas> listado() throws NegocioException {
        try {
            return personasDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }


    @Override
    public Personas cargar(long id) throws NegocioException, NoEncontradoException {
        Optional<Personas> o;
        try {
            o = personasDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        if (!o.isPresent()) {
            throw new NoEncontradoException("No se encuentra la persona con id=" + id);
        }
        return o.get();
    }


    @Override
    public Personas agregar(Personas personas) throws NegocioException {

            try {
                return personasDAO.save(personas);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new NegocioException(e);
            }

    }


    @Override
    public Personas modificar(Personas personas) throws NegocioException {

        try {
            return personasDAO.save(personas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }


    @Override
    public void eliminar(long id) throws NegocioException, NoEncontradoException {
        cargar(id);
        try {
            personasDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }


}
