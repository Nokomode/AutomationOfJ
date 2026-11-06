package Software.Engineering.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "menuitem")
public class MenuItem {

    public MenuItem() {

    }

    public MenuItem(String menuItemName, double pricePerUnit, int timesOrdered) {
        this.menuItemName = menuItemName;
        this.pricePerUnit = pricePerUnit;
        this.timesOrdered = timesOrdered;
    }

    @Id
    @Column(name = "Menu_Item_Name")
    private String menuItemName;

    @Column(name = "Price_Per_Unit")
    private double pricePerUnit;

    @Column(name = "Times_Ordered")
    private int timesOrdered = 1;

    /// Getters and setters
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
        this.pricePerUnit = price;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }
}

