package Software.Engineering.demo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Shift_ID")
    private int shiftID;

    @ManyToOne
    @JoinColumn(name = "Employee_ID", nullable = false)
    private String employeeID;

    @Column (name = "Date")
    private LocalDate date;

    @Column (name = "Start_Time")
    private LocalDateTime startTime;

    @Column (name = "End_Time")
    private LocalDateTime endTime;


    public Shift() {

    }

    public Shift(LocalDateTime startTime, LocalDateTime endTime, int shiftId, String employeeId, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftID = shiftId;
        this.employeeID = employeeId;
        this.date = date;
    }

    public Shift(LocalDateTime startTime, LocalDateTime endTime, String employeeId, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.employeeID = employeeId;
        this.date = date;
    }

    public Shift(LocalDateTime endTime, int shiftId) {
        this.endTime = endTime;
        this.shiftID = shiftId;
    }


    // Getters and Setters
    public int getShiftId() {
        return shiftID;
    }
    public void setShiftID(int shiftId) { this.shiftID = shiftId; }

    public String getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getDate() { return this.date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }




}
