package Software.Engineering.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {


    private MenuItemRepository itemRepo;

    MenuService(MenuItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    /// returns all items from the database
    public List<MenuItem> getAllMenuItems() {
        return itemRepo.findAll();
    }

    /// returns result of querying the menu table by a menu item name
    public MenuItem getMenuItemByName(String name) {
        return itemRepo.findById(name).orElse(null);
    }

    /// returns the amount of times a menu item has been ordered, -1 if the query failed
    public int getTimesOrderedByName(String name) {
        MenuItem item = itemRepo.findById(name).orElse(null);
        if (item != null) {
            return item.getTimesOrdered();
        } else {
            return -1;
        }
    }

    /// adds to the amount of times ordered for a certain menu item. returns false it the query for that item fails
    public boolean addTimesOrderedByName(String name, int orders) {
        MenuItem item = itemRepo.findById(name).orElse(null);
        if(item != null) {
            item.setTimesOrdered(item.getTimesOrdered() + orders);
            itemRepo.save(item);
            return true;
        } else {
            return false;
        }
    }
}

