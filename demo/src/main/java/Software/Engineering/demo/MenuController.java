package Software.Engineering.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /// Sends all menu items from the database to the frontend in the form of a list, or null if the query failed
    @GetMapping("/getAllMenuItems")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {

        List<MenuItem> result = menuService.getAllMenuItems();

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends a single menu item by its name to the frontend or null if the query for that item failed
    @GetMapping("/getMenuItemByName/{name}")
    public ResponseEntity<MenuItem> getMenuItemByName(@PathVariable String name) {

        MenuItem result = menuService.getMenuItemByName(name);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends the amount of times a menu item has been ordered to the frontend or -1 if the query for that item failed
    @GetMapping("/getTimesOrderedByName/{name}")
    public ResponseEntity<Integer> getTimesOrderedByName(@PathVariable String name) {
        int result = menuService.getTimesOrderedByName(name);

        if (result >= 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1);
        }

    }

    /// Adds to the amount of times ordered for a certain item, returns true to the frontend if successful and false otherwise
    @PostMapping("/addTimesOrdered")
    public ResponseEntity<Boolean> addTimesOrderedByName(@RequestBody MenuItemRequest req) {
        boolean result = menuService.addTimesOrderedByName(req.getMenuItemName(), req.getTimesOrdered());

        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

    }
}
