package Software.Engineering.demo;


import jakarta.persistence.*;

@Entity
@Table(name = "itemorder")
public class ItemOrder {

    public ItemOrder(int itemOrderNo, Order order, MenuItem menuItem, int quantity, double price, String notes) {
        this.itemOrderNo = itemOrderNo;
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.price = price;
        this.notes = notes;
    }

    public ItemOrder(Order order, MenuItem menuItem, int quantity, double price, String notes) {
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.price = price;
        this.notes = notes;
    }

    public ItemOrder() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ItemOrder_NO`")
    private int itemOrderNo;

    @ManyToOne
    @JoinColumn(name = "Order_NO", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "Item_Name")
    private MenuItem menuItem;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private double price;

    @Column(name = "Notes")
    private String notes;


    /// Getters and setters below

    public int getItemOrderNo() { return itemOrderNo; }
    public void setItemOrderNo(int itemOrderNo) { this.itemOrderNo = itemOrderNo; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
