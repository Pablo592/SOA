package ar.edu.iua.iw3.DAO;

import java.util.Optional;

import ar.edu.iua.iw3.Model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface DirectionRepository  extends JpaRepository<Direction,Long> {

	Optional<Direction> findById(Long direction_id);

}

