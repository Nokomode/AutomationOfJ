package Software.Engineering.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
public class TableController {

    private TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    /// sends all tables to the frontend, sends null if the search fails for some reason
    @GetMapping("/getAllTables")
    public ResponseEntity<List<RTable>> getAllTables() {

        List<RTable> result = tableService.getAllTables();

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends a table to the frontend by a tableId, sends null if the search fails
    @GetMapping("/getTableByTableId/{tableID}")
    public ResponseEntity<RTable> getTable(@PathVariable String tableID) {

        RTable result = tableService.getTableById(tableID);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends alist of tables assigned to a certain employee to the frontend, sends null if the search fails
    @GetMapping("/getTablesByEmployeeId/{employeeID}")
    public ResponseEntity<List<RTable>> getTableByEmployeeId(@PathVariable String employeeID) {
        List<RTable> result = tableService.getTableByEmployeeId(employeeID);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// assigns a table to a certain employee, sends a bad request to the frontend if the search fails
    @PostMapping("/assignTable")
    public ResponseEntity<String> assignTable(@RequestBody TableRequest req) {

        boolean result = tableService.assignTable(req.getTableId(), req.getEmployeeId());

        if (result) {
            return ResponseEntity.ok("Table assigned successfully" );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    /// updates the status of a table, sends a bad request to the frontend if the search for a table fails
    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestBody TableRequest req) {
        System.out.println(req.getTableId() + req.getStatus() + req.getEmployeeId());
        boolean result = tableService.updateStatus(req.getTableId(), req.getStatus());

        if (result) {
            return ResponseEntity.ok("Table status updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    /* Not putting a priority on these functions unless we have extra time
    @PostMapping("/addSeats")
    public ResponseEntity<RTable> addSeats(@RequestBody TableRequest req) {

        RTable result = tableService.addSeats(req.getTableID(), req.getSeats());

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/combine")
    public ResponseEntity<RTable> combineTables(@RequestBody TableRequest req) {

        RTable result = tableService.combineTables(req.getTableID(), req.getEmployeeID());

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    */

}

