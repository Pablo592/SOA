package ar.edu.iua.iw3.negocio;

import ar.edu.iua.iw3.modelo.persistencia.Usuario;
import ar.edu.iua.iw3.modelo.repository.UsuarioRepository;
import ar.edu.iua.iw3.negocio.excepciones.NegocioException;
import ar.edu.iua.iw3.negocio.excepciones.NoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioNegocio  implements IUsuarioNegocio{

    private static Logger log = LoggerFactory.getLogger(UsuarioNegocio.class);

    @Autowired
    private static UsuarioRepository usuarioDAO;

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
}
