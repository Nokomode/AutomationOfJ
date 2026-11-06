package Software.Engineering.demo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepo;

    public InventoryService(InventoryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    /// Returns an item by querying its name; null if not found
    public InventoryItem getItemByName(String name){
        return inventoryRepo.findById(name).orElse(null);

    }

    /// Returns all items out of the inventory database table
    public List<InventoryItem> getAllInventory() {
        return inventoryRepo.findAll();
    }

    /// Saves the new greater quantity of a certain item to the database, returns false if item search fails.
    public boolean addToInventory(String name, int quantity) {
        System.out.println(name);
        InventoryItem item = inventoryRepo.findById(name).orElse(null);

        if(item != null) {
            item.setQuantity(quantity);
            inventoryRepo.save(item);
            return true;
        } else {
            return false;
        }
    }

    /// Saves the new lesser quantity of a certain item to the database, returns false if item search fails
    public boolean takeFromInventory(String name, int quantity) {
        InventoryItem item = inventoryRepo.findById(name).orElse(null);

        if(item != null) {
            item.setQuantity(item.getQuantity() - quantity);
            inventoryRepo.save(item);
            return true;
        } else {
            return false;
        }
    }
}