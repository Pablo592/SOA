package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.persistencia.Envio;
import ar.edu.iua.iw3.modelo.respository.EnvioRepository;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioNegocio implements IEnvioNegocio {

    private static Logger log = LoggerFactory.getLogger(EnvioNegocio.class);

    @Autowired
    private EnvioRepository envioDAO;

    @Override
    public List<Envio> cargar(long id) throws NegocioException, NoEncontradoException {
        Optional<List<Envio>> o;
        try {
            o = envioDAO.findByUser(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        if (!o.isPresent()) {
            throw new NoEncontradoException("No se encuentra el envio con id=" + id);
        }
        return o.get();    }
}
