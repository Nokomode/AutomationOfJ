package Software.Engineering.demo;
/// Data transfer object for menu items
public class MenuItemRequest {

    private String menuItemName;
    private int pricePerUnit;
    private int timesOrdered;

    /// Getters and setters.

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String name) {
        this.menuItemName = name;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double price) {
        this.pricePerUnit = (int) price;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }
}

