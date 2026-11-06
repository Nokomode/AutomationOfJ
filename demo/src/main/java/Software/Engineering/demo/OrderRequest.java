package Software.Engineering.demo;

/// Data transfer object for orders
public class OrderRequest {
    public String employeeId;
    public String tableId;
    public double totalPrice;

    public OrderRequest(String employeeId, String tableId, double totalPrice) {
        this.employeeId = employeeId;
        this.tableId = tableId;
        this.totalPrice = totalPrice;
    }
}
