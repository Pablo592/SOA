package ar.edu.iua.iw3.modelo.repository;

import ar.edu.iua.iw3.modelo.persistencia.Personas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonasRepository extends JpaRepository<Personas, Long> {
}
