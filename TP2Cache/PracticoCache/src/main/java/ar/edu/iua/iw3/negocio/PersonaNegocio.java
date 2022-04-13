package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.cache.Memcache;
import ar.edu.iua.iw3.modelo.persistencia.Persona;
import ar.edu.iua.iw3.modelo.repository.PersonaRepository;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaNegocio implements IPersonaNegocio {

    private Logger log = LoggerFactory.getLogger(PersonaNegocio.class);
    private Memcache cache = new Memcache();


    @Autowired
    private PersonaRepository personaDAO;

    @Override
    public List<Persona> listado() throws NegocioException {
        try {
            return personaDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }


    @Override
    public Persona cargar(long id) throws NegocioException, NoEncontradoException {
        Optional<Persona> o = null;
        try {
            if(cache.buscar(id) == null) {
                o = personaDAO.findById(id);
                cache.agregar(o.get(), 3600000);
            }else{
                o = (Optional<Persona>) cache.buscar(id);
            }
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
    public Persona agregar(Persona persona) throws NegocioException {
            try {
                return personaDAO.save(persona);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new NegocioException(e);
            }
    }


    @Override
    public Persona modificar(Persona persona) throws NegocioException {
        try {
            cache.actalizar(persona,3600000);
            return personaDAO.save(persona);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }


    @Override
    public void eliminar(long id) throws NegocioException, NoEncontradoException {
        cargar(id);
        try {
            cache.eliminar(id);
            personaDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }


}
