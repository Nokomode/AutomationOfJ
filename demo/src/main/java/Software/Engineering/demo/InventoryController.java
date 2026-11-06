package Software.Engineering.demo;


import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /// Gets a name for an inventory item from the frontend and calls the inventory service to search for that item and returns that item if found.
    @GetMapping("/getInventoryItemByItemName/{name}")
    public ResponseEntity<InventoryItem> getByItemName(@PathVariable String name) {

        InventoryItem item = inventoryService.getItemByName(name);

        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Attempts to send all inventory items to the frontend, sends null if it failed.
    @GetMapping("/getAllInventoryItems")
    public ResponseEntity<List<InventoryItem>> getAllInventory() {

        List<InventoryItem> items = inventoryService.getAllInventory();
        if (!items.isEmpty()) {
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    /// Adds to the amount of inventory of a specific item, if the specific item is not found it returns null
    @PostMapping("/addToInventory")
    public ResponseEntity<String> addToInventory(@RequestBody InventoryRequest req) {
        System.out.println(req.getItemName());
        boolean success = inventoryService.addToInventory(req.getItemName(), req.getQuantity());

        if (success) {
            return ResponseEntity.ok("Added to Inventory!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Takes a specified amount of inventory out of the specified item
    @PostMapping("/takeFromInventory/{quantity}")
    public ResponseEntity<String> takeFromInventory(@RequestBody InventoryRequest req, @PathVariable int quantity) {
        boolean success = inventoryService.takeFromInventory(req.getItemName(), quantity);

        if (success) {
            return ResponseEntity.ok("Taken from inventory");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
