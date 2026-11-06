package Software.Engineering.demo;


import jakarta.persistence.*;

@Entity
@Table(name = "`table`")
public class RTable {

    public RTable() {

    }

    public RTable(String tableId, User employee, String status) {
        this.tableId = tableId;
        this.employee = employee;
        this.status = status;
    }


    @Id
    @Column(name = "Table_ID")
    private String tableId;

    @ManyToOne
    @JoinColumn(name = "Assigned_Employee_ID")
    private User employee;

    @Column(name = "Status")
    private String status;

    /// Getters and setters
    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
