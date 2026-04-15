package Software.Engineering.demo;

import java.util.List;
import java.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {
    
    private final ShiftService shiftService;
    
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping("/getByShiftID")
    public ResponseEntity<String> getShiftByShiftID(@RequestParam int shiftID) {

        Shift shift = shiftService.getShiftByShiftID(shiftID);
        if (shift != null) {
            return ResponseEntity.ok(shift.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }

    @PostMapping("/getShiftsByEmployeeID")
    public ResponseEntity<String> getShiftsByEmployeeId(@RequestParam String employeeID) {
        List<Shift> shifts = Arrays.asList(shiftService.getShiftByEmployeeId(employeeID));

        return ResponseEntity.ok(shifts.toString());
    }

    @PostMapping("/getAllShifts")
    public ResponseEntity<List<Shift>> getAllShifts() {

        List<Shift> shifts = Arrays.asList(shiftService.getAllShifts());
        return ResponseEntity.ok(shifts);
    }

    @PostMapping("/addShift")
    public ResponseEntity<String> addShift(@RequestBody Shift shift) {
       shiftService.addShift(shift);
       return ResponseEntity.ok("Shift added successfully");
    }

}



