package Software.Engineering.demo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepo;

    //Handles shift related requests then moves it to the shift repo to handle the database.
    public ShiftService(ShiftRepository shiftRepo) {
        this.shiftRepo = shiftRepo;
    }

    //Looks through all of the shifts and seaches for the shift with the matching shift ID. If it is found the shift is returned, if not it returns null.
    public Shift getShiftByShiftID(int shiftID) {
        return shiftRepo.findById(shiftID).orElse(null);
    }

    //Searches through all of the shifts based on the employee ID and returns an array of that employee's shifts. 
    public Shift[] getShiftByEmployeeId(String employeeId){

        List<Shift> shifts = shiftRepo.findByEmployeeIdEmployeeID(employeeId);
        return shifts.toArray(new Shift[0]);

    }

    //The shift will be added to the database with the repository.
    public void addShift(Shift shift){
        shiftRepo.save(shift);
    }

    //All shifts in the dabase will be returned as a list.
    public List<Shift> getAllShifts() {
        List<Shift> allShifts = shiftRepo.findAll();
        return allShifts;
    }

    
}
