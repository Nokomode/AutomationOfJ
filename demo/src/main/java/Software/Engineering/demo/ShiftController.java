package Software.Engineering.demo;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/shifts")
public class ShiftController {
    
    private final ShiftService shiftService;
    private final UserRepository userRepository;
    
    //Handles requests related to shifts, like searching for a shift by employee ID or shift ID, or everything as a whole.
    public ShiftController(ShiftService shiftService, UserRepository userRepository) {
        this.shiftService = shiftService;
        this.userRepository = userRepository;
    }

    //This function looks through the shifts and finds the shift by its shift ID.
    @PostMapping("/getByShiftID")
    public ResponseEntity<Shift> getShiftByShiftID(@RequestBody ShiftRequest request) {

        Shift shift = shiftService.getShiftByShiftID(request.getShiftID());
        if (shift != null) {
            return ResponseEntity.ok(shift);
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }

    //This function goes through all of the shifts and finds the shifts that are linked to the employee ID.
    @PostMapping("/getShiftsByEmployeeID")
    public ResponseEntity<List<Shift>> getShiftsByEmployeeId(@RequestBody ShiftRequest request) {
        List<Shift> shifts = Arrays.asList(shiftService.getShiftByEmployeeId(request.getEmployeeID()));

        return ResponseEntity.ok(shifts);
    }

    //This function will put all of the shifts into a list and return it.
    @PostMapping("/getAllShifts")
    public ResponseEntity<List<Shift>> getAllShifts() {

        List<Shift> shifts = shiftService.getAllShifts();
        return ResponseEntity.ok(shifts);
    }


    //Dont really need an add shift method, but its here just in case
    /*@PostMapping("/addShift")
    public ResponseEntity<String> addShift(@RequestBody ShiftRequest request) {

       Optional<User> user = userRepository.findById(request.getEmployeeID());
       Shift shift = new Shift(request.getStartTime(), request.getEndTime(), request.getShiftID(), user, request.getDate());
       shiftService.addShift(shift);
       return ResponseEntity.ok("Shift added successfully");
    }*/

}



