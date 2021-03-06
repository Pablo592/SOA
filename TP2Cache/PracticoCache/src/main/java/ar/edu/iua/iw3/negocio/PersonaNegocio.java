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

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
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
    public List<Persona> listarContenidoNombre(String letras) throws NegocioException, NoEncontradoException, JsonProcessingException {
        String encuentro;
        String[] indices;
        String indice = "";
        Optional<List<Persona>> o = null;
        List<Persona> pers = new ArrayList<Persona>();
        String datoBuscado = cache.buscarLetras("Lista"+ letras);
        if(datoBuscado == null || datoBuscado.length() < 2) {
            try {
                o = Optional.ofNullable(personaDAO.findAllByNombreContains(letras));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new NegocioException(e);
            }
            if (!o.isPresent()) {
                throw new NoEncontradoException("No se encuentra la persona con el nombre con las letras " + letras);
            }else {

                for (Persona p : o.get()) {
                 indice =  indice.concat(p.getId() + ",");
                    cache.agregar(p, 3600);
                }
                System.out.println("Datos de la lista agregados al cache");
                cache.agregarLista("Lista"+ letras,indice, 3600);
            }
        }else{
            indices = datoBuscado.split(",");

            for (int i = 0; i < indices.length - 1; i++) {

                    encuentro = cache.buscar(Long.valueOf(indices[i].trim()));
                      //      if(encuentro != null || encuentro.length() > 10)
                    pers.add(new ObjectMapper().readValue(encuentro, Persona.class));
            }
            System.out.println("Datos de la lista sacados del cache");
            o = Optional.ofNullable(pers);
        }
        return o.get();
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
                System.out.println(o.get() + "Guardado en el cache */*/*/*/*/*/*/");
            }
        }else{
            Persona persona = new ObjectMapper().readValue(datoBuscado, Persona.class);
            o = Optional.ofNullable(persona);
            System.out.println(o.get() + " Sacado del cache");
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
