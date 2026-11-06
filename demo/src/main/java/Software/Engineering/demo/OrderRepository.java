package Software.Engineering.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /// Parses to SQL query to find orders by either their assigned employee or table ids
    List<Order> findByUserEmployeeId(String id);
    List<Order> findByTableTableId(String id);
}
