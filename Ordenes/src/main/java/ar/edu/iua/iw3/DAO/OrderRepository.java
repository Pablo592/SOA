package ar.edu.iua.iw3.DAO;

import java.util.Optional;

import ar.edu.iua.iw3.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface OrderRepository  extends JpaRepository<Order,Long> {

	Optional<Order> findById(Long order_id);
	Integer countDistinctIdOrderByUserId(Long user_id);

}

