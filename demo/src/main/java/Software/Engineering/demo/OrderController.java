package Software.Engineering.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /// Sends an ItemOrder to the frontend by its ItemOrderId or null if the search fails
    @GetMapping("/getItemOrderByItemOrderId/{id}")
    public ResponseEntity<ItemOrder> getItemOrderByItemOrderId(@PathVariable int id) {
        ItemOrder result = orderService.getItemOrderByItemOrderId(id);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends a list of ItemOrders to the frontend by their associated OrderId, sends null is search fails
    @GetMapping("/getItemOrdersByOrderId/{id}")
    public ResponseEntity<List<ItemOrder>> getItemOrdersByOrderId(@PathVariable int id) {
        List<ItemOrder> result = orderService.getItemOrdersByOrderId(id);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends a list of Orders to the frontend by their associated EmployeeId, sends null if search fails
    @GetMapping("/getOrdersByEmployeeId/{id}")
    public ResponseEntity<List<Order>> getOrdersByEmployeeId(@PathVariable String id) {
        List<Order> result = orderService.getOrdersByEmployeeId(id);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends a list of orders to the frontend by their associated tableid, sends null if search fails
    @GetMapping("/getOrdersByTableId/{id}")
    public ResponseEntity<List<Order>> getOrdersByTableId(@PathVariable String id) {
        List<Order> result = orderService.getOrdersByTableId(id);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends a single order by its OrderId to the frontend, sends null if search fails
    @GetMapping("/getOrderByOrderId/{id}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable int id) {
        Order result = orderService.getOrderByOrderId(id);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends all item orders to the frontend, sends null if search fails
    @GetMapping("/getAllItemOrders")
    public ResponseEntity<List<ItemOrder>> getAllItemOrders() {
        List<ItemOrder> result = orderService.getAllItemOrders();

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// sends all orders to the frontend, sends null if failed
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> result = orderService.getAllOrders();

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sets an order as ready by its order id, returns a not found HTTP error to the frontend if search fails
    @GetMapping("/orderReady/{id}")
    public ResponseEntity<String> orderReady(@PathVariable int id) {
        boolean success = orderService.orderReady(id);

        if (success) {
            return ResponseEntity.ok("Order set to ready.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    /// Sets refund for an order by id orderid to true, sends not found HTTP error to the frontend if search fails
    @GetMapping("/refundOrder/{id}")
    public ResponseEntity<String> refundOrder(@PathVariable int id) {
        boolean success = orderService.refundOrder(id);

        if (success) {
            return ResponseEntity.ok("Order refunded.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    /// Adds an order to the database based on information gathered from the frontend. Returns not found if for any reason the add fails
    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrder(@RequestBody OrderRequest req) {
        boolean result = orderService.addOrder(req.employeeId, req.tableId, req.totalPrice);

        if (result) {
            return ResponseEntity.ok("Order added");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Adds an item order to the database based on information gathered from the frontend, sends not found error if for any reason the add fails
    @PostMapping("/addItemOrder")
    public ResponseEntity<String> addItemOrder(@RequestBody ItemOrderRequest req) {
        System.out.println(req.orderNo + " - " + req.menuItemId + " - " + req.price + " - " + req.quantity); //debug print
        boolean result = orderService.addItemOrder(req.orderNo, req.menuItemId, req.quantity, req.price);

        if (result) {
            return ResponseEntity.ok("Item Order Added");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
