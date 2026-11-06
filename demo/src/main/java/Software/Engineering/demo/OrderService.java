package Software.Engineering.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final ItemOrderRepository itemOrderRepo;
    private final MenuItemRepository menuItemRepo;
    private final UserRepository userRepo;
    private final TableRepository tableRepo;

    public OrderService(OrderRepository orderRepo, ItemOrderRepository itemOrderRepo, MenuItemRepository menuItemRepo, UserRepository userRepo, TableRepository tableRepo) {
        this.orderRepo = orderRepo;
        this.itemOrderRepo = itemOrderRepo;
        this.menuItemRepo = menuItemRepo;
        this.userRepo = userRepo;
        this.tableRepo = tableRepo;
    }

    ///  Returns an item order by its item order id
    public ItemOrder getItemOrderByItemOrderId(int id) {
        return itemOrderRepo.findById(id).orElse(null);
    }

    ///  returns a list of item orders by their common order id
    public List<ItemOrder> getItemOrdersByOrderId(int id) {
        return itemOrderRepo.findByOrderOrderNo(id);
    }

    /// Returns a list of orders by their common employee id
    public List<Order> getOrdersByEmployeeId(String id) {
        return orderRepo.findByUserEmployeeId(id);
    }

    ///  Returns a list of orders by their common table id
    public List<Order> getOrdersByTableId(String id) {
        return orderRepo.findByTableTableId(id);
    }

    /// Returns an order by its order id
    public Order getOrderByOrderId(int id) {
        return orderRepo.findById(id).orElse(null);
    }

    /// returns fall item orders
    public List<ItemOrder> getAllItemOrders() {
        return itemOrderRepo.findAll();
    }

    ///  returns all orders
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    /// Sets an order as ready, returns false if assignment fails
    public boolean orderReady(int id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order != null) {
            order.setReadyAt(LocalDateTime.now());
            orderRepo.save(order);
            return true;
        } else {
            return false;
        }
    }


    /// Sets an order as refunded, returns false if the assignment fails
    public boolean refundOrder(int id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order != null) {
            order.setRefunded(true);
            orderRepo.save(order);
            return true;
        } else {
            return false;
        }
    }

    ///  Adds an item order to the database, returns false if the addition fails
    public boolean addItemOrder(int orderNo, String menuItemId, int quantity, double price) {
        MenuItem item = menuItemRepo.findById(menuItemId).orElse(null);
        Order order = orderRepo.findById(orderNo).orElse(null);

        if (item != null && order != null) {
            ItemOrder itemOrder = new ItemOrder(order, item, quantity, price, " ");
            itemOrderRepo.save(itemOrder);
            return true;
        } else {
            return false;
        }
    }

    /// Adds an order to the database, returns false if the add fails
    public boolean addOrder(String employeeId, String tableId, double totalPrice) {
        User user = userRepo.findById(employeeId).orElse(null);
        RTable table = tableRepo.findById(tableId).orElse(null);
        System.out.println("Table id: " + tableId);
        System.out.println("employeeId" + employeeId);

        if (user != null && table != null) {
            Order order = new Order(user, LocalDateTime.now(), table, 0, false, null, totalPrice);
            orderRepo.save(order);
            return true;
        } else {
            return false;
        }
    }
}
