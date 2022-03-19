package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.DTO.UsuarioDatosDTO;
import ar.edu.iua.iw3.modelo.persistencia.Usuario;
import ar.edu.iua.iw3.modelo.repository.UsuarioRepository;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import ar.edu.iua.iw3.web.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioNegocio  implements IUsuarioNegocio{

    private static Logger log = LoggerFactory.getLogger(UsuarioNegocio.class);

    @Autowired
    private  UsuarioRepository usuarioDAO;

    @Autowired
    private RestTemplate enviosRestTemplate;

    @Override
    public Usuario cargar(long id) throws NegocioException, NoEncontradoException {
        Optional<Usuario> o;
        try {
            o = usuarioDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioException(e);
        }
        if (!o.isPresent()) {
            throw new NoEncontradoException("No se encuentra el usuario con id=" + id);
        }
        return o.get();    }

    public UsuarioDatosDTO usuarioDatos(long id) throws NegocioException, NoEncontradoException {
        List<Object>  direcciones = null;
        UsuarioDatosDTO userDTO = new UsuarioDatosDTO(cargar(id),direcciones,5, enviosRestTemplate.getEnviosList(String.valueOf(id)).toString().contains("pendiente"));
        return userDTO;    }
}
