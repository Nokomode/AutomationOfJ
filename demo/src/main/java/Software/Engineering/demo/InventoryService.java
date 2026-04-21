package Software.Engineering.demo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepo;

    public InventoryService(InventoryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }
public InventoryItem getItemByName(String name){
    return inventoryRepo.findByItemName(name).orElse(null);

}

  public InventoryItem[] getAllInventory() {
        List<InventoryItem> items = inventoryRepo.findAll();
        return items.toArray(new InventoryItem[0]);
    }


}

