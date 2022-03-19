package ar.edu.iua.iw3.modelo.respository;

import ar.edu.iua.iw3.modelo.persistencia.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository   extends JpaRepository<Envio, Long> {
    Optional<List<Envio>>findByUser(long id);
}
