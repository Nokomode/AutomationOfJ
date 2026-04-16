package Software.Engineering.demo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepo;

    public ShiftService(ShiftRepository shiftRepo) {
        this.shiftRepo = shiftRepo;
    }

    public Shift getShiftByShiftID(int shiftID) {
        return shiftRepo.findById(shiftID).orElse(null);
    }

    public Shift[] getShiftByEmployeeId(String employeeId){

        List<Shift> shifts = shiftRepo.findByEmployeeIdEmployeeID(employeeId);
        return shifts.toArray(new Shift[0]);

    }

    public void addShift(Shift shift){
        shiftRepo.save(shift);
    }

    public List<Shift> getAllShifts() {
        List<Shift> allShifts = shiftRepo.findAll();
        return allShifts;
    }

    
}
