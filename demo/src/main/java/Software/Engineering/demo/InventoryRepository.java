package Software.Engineering.demo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, String>{
    /// findById predefined by JpaRepository, do not need to define it here.
}
