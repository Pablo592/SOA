package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.cache.Memcache;
import ar.edu.iua.iw3.modelo.persistencia.Persona;
import ar.edu.iua.iw3.modelo.repository.PersonaRepository;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaNegocio implements IPersonaNegocio {

    private Logger log = LoggerFactory.getLogger(PersonaNegocio.class);
    private Memcache cache = new Memcache();


    @Autowired
    private PersonaRepository personaDAO;

    public PersonaNegocio() throws IOException {
    }

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
    public Persona cargar(long id) throws NegocioException, NoEncontradoException, JsonProcessingException {
        Optional<Persona> o = null;
        String datoBuscado = cache.buscar(id);
        if(datoBuscado == null || datoBuscado == "") {
            try {
                o = personaDAO.findById(id);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new NegocioException(e);
            }
            if (!o.isPresent()) {
                throw new NoEncontradoException("No se encuentra la persona con id=" + id);
            }else {
                cache.agregar(o.get(), 3600);
            }
        }else{
            Persona persona = new ObjectMapper().readValue(datoBuscado, Persona.class);
            o = Optional.ofNullable(persona);
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
            String datoBuscado = cache.buscar(persona.getId());
            if(datoBuscado != null) {
                cache.actualizar(persona, 3600);
            }
            return personaDAO.save(persona);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
    }


    @Override
    public void eliminar(long id) throws NegocioException, NoEncontradoException, JsonProcessingException {
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
