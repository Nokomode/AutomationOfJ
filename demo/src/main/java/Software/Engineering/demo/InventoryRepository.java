package Software.Engineering.demo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//This is a custom function to find the inventory item by its name.
    @Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, String>{
    Optional<InventoryItem> findByItemName(String itemName);
}

