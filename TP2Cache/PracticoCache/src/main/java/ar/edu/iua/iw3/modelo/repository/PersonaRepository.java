package ar.edu.iua.iw3.modelo.repository;

import ar.edu.iua.iw3.modelo.persistencia.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findAllByNombreContains(String letras);

    //@Query(value ="SELECT * FROM Personas.personas where nombre = ?1", nativeQuery = true)
    //public List<Persona> findTheNombreContais(String id);

}
 //like '%=:letras%'