package ar.edu.iua.iw3.modelo.repository;

import ar.edu.iua.iw3.modelo.persistencia.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Long id);

}