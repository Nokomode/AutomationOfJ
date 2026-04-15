package Software.Engineering.demo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Entity
@Table(name = "shift")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Shift_ID")
    private int shiftID;

    @ManyToOne
    @JoinColumn(name = "Employee_ID", nullable = false)
    private User employee;

    @Column (name = "Date")
    private LocalDate date;

    @Column (name = "Start_Time")
    private LocalDateTime startTime;

    @Column (name = "End_Time")
    private LocalDateTime endTime;


    public Shift() {

    }

    public Shift(LocalDateTime startTime, LocalDateTime endTime, int shiftId, User employee, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftID = shiftId;
        this.employee = employee;
        this.date = date;
    }

    public Shift(LocalDateTime startTime, LocalDateTime endTime, User employee, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
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

    public User getEmployee() {
        return employee;
    }
    public void setEmployeeID(User employee) {
        this.employee = employee;
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
