package Software.Engineering.demo;


import java.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/getByItemName")
    public ResponseEntity<String> getByItemName(@RequestParam String name) {

        InventoryItem item = inventoryService.getItemByName(name);

        if (item != null) {
            return ResponseEntity.ok(item.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/getAllInventory")
    public ResponseEntity<String> getAllInventory() {

        InventoryItem[] items = inventoryService.getAllInventory();
        return ResponseEntity.ok(Arrays.toString(items));

    }
}
    


