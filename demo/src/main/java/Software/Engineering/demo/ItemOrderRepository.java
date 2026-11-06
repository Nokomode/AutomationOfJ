package Software.Engineering.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {
    /// Used to generate a SQL query to search the ItemOrder table by OrderNo column
    List<ItemOrder> findByOrderOrderNo(int id);
}
