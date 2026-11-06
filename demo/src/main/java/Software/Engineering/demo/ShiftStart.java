package Software.Engineering.demo;
import java.time.LocalDateTime;
import java.time.LocalTime;

/// Static class that grabs the starting time of the current employee
public class ShiftStart {
    public static LocalTime startTime;

    public static void setStartTime(LocalTime start) { startTime = start; }
}

