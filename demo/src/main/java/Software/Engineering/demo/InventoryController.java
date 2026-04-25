package Software.Engineering.demo;


import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Handles any inventory requests like searching for an item or the whole inventory.
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //This function works through the inventory and searches for the specified item.
    @PostMapping("/getByItemName")
    public ResponseEntity<String> getByItemName(@RequestParam String name) {

        InventoryItem item = inventoryService.getItemByName(name);

        if (item != null) {
            return ResponseEntity.ok(item.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //This funtion will return the whole inventory as a list.
    @PostMapping("/getAllInventory")
    public ResponseEntity<String> getAllInventory() {

        List<InventoryItem> items = inventoryService.getAllInventory();

        if(items.isEmpty()) {
        return ResponseEntity.ok(items.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  
        }
    }
}
    


