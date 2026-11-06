package Software.Engineering.demo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepo;

    public ShiftService(ShiftRepository shiftRepo) {
        this.shiftRepo = shiftRepo;
    }

    /// returns a shift by its shift id or null if search fails
    public Shift getShiftByShiftID(int shiftID) {
        return shiftRepo.findById(shiftID).orElse(null);
    }

    /// returns a list of shifts for a certain employee; empty if search fails
    public List<Shift> getShiftByEmployeeId(String employeeId) {
        return shiftRepo.findByEmployeeEmployeeId(employeeId);
    }

    /// adds a shift to the database
    public void addShift(Shift shift){
        shiftRepo.save(shift);
    }

    /// returns all shifts from the database
    public List<Shift> getAllShifts() {
        List<Shift> allShifts = shiftRepo.findAll();
        return allShifts;
    }



}
