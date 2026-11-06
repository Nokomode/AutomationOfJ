package Software.Engineering.demo;

/// Data transfer object for table information
public class TableRequest {

    private String tableId;
    private String status;
    private String employeeId;

    /// Getters and setters
    public String getTableId() { return tableId; }
    public void setTableId(String id) { this.tableId = id; }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeID(String id) { this.employeeId = id; }
}


