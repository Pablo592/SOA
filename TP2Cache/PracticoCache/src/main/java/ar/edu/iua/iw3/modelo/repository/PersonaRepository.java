package ar.edu.iua.iw3.modelo.repository;

import ar.edu.iua.iw3.modelo.persistencia.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
