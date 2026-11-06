package Software.Engineering.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TableService {

    private TableRepository tableRepo;
    private UserRepository userRepository;

    public TableService(TableRepository tableRepo, UserRepository userRepo) {
        this.tableRepo = tableRepo;
        this.userRepository = userRepo;
    }

    /// Gets all tables
    public List<RTable> getAllTables() {
        return tableRepo.findAll();
    }

    ///  gets a table by its table id
    public RTable getTableById(String tableID) {
        return tableRepo.findById(tableID).orElse(null);
    }
    ///  gets a list of tables by their assigned employee id
    public List<RTable> getTableByEmployeeId(String id) {

        return tableRepo.findByEmployeeEmployeeId(id);
    }


    /// Assigns a table to an employee, returns false if it fails
    public boolean assignTable(String tableID, String employeeID) {
        RTable table = tableRepo.findById(tableID).orElse(null);
        User user = userRepository.findById(employeeID).orElse(null);

        if (table != null) {
            table.setEmployee(user);
            tableRepo.save(table);
            return true;
        }
        return false;
    }
    /// updates the status of a table, returns false if the search for a table object fails
    public boolean updateStatus(String tableID, String status) {
        RTable table = tableRepo.findById(tableID).orElse(null);

        if (table != null) {
            table.setStatus(status);
            tableRepo.save(table);
            return true;
        }
        return false;
    }

    /* Not putting a priority on these functions unless we have extra time
    public RTable addSeats(String tableID, int seats) {
        RTable table = tableRepo.findById(tableID).orElse(null);

        if (table != null) {
            table.set(table.getSeats() + seats);
            return tableRepo.save(table);
        }
        return null;
    }

    public RTable combineTables(String t1, String t2) {
        RTable table1 = tableRepo.findById(t1).orElse(null);
        RTable table2 = tableRepo.findById(t2).orElse(null);

        if (table1 != null && table2 != null) {
            table1.setSeats(table1.getSeats() + table2.getSeats());
            tableRepo.delete(table2);
            return tableRepo.save(table1);
        }
        return null;
    } */
}