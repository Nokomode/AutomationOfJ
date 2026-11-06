package Software.Engineering.demo;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;


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
    private LocalTime startTime;

    @Column (name = "End_Time")
    private LocalTime endTime;


    public Shift() {

    }

    public Shift(LocalTime startTime, LocalTime endTime, int shiftId, User employee, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftID = shiftId;
        this.employee = employee;
        this.date = date;
    }

    public Shift(LocalTime startTime, LocalTime endTime, User employee, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
        this.date = date;
    }

    public Shift(LocalTime endTime, int shiftId) {
        this.endTime = endTime;
        this.shiftID = shiftId;
    }


    /// Getters and Setters
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

    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }




}
