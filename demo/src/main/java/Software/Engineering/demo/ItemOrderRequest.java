package Software.Engineering.demo;
/// Data transfer object for item orders
public class ItemOrderRequest {

    public int orderNo;
    public String menuItemId;
    public int quantity;
    public double price;

    public ItemOrderRequest(int orderNo, String menuItemId, int quantity, double price) {
        this.orderNo = orderNo;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.price = price;
    }

    public ItemOrderRequest() {

    }


    /// Getters and setters
    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }
}
