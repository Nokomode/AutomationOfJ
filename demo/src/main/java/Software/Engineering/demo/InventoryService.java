package Software.Engineering.demo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepo;

    public InventoryService(InventoryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    //This function searches through the inventory and returns the searched item if it is found, if not it returns null.
public InventoryItem getItemByName(String name){
    return inventoryRepo.findById(name).orElse(null);

}

//This function returns the  inventory as a list.
  public List<InventoryItem> getAllInventory() {
        return inventoryRepo.findAll();
    }


}

