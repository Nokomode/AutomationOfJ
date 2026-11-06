package Software.Engineering.demo;
///  Data transfer object for inventory items
public class InventoryRequest {

    private String itemName;
    private int quantity;


    ///Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
