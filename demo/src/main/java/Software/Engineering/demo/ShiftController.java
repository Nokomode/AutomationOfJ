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

    public ShiftController(ShiftService shiftService, UserRepository userRepository) {
        this.shiftService = shiftService;
        this.userRepository = userRepository;
    }

    @PostMapping("/getByShiftID")
    public ResponseEntity<Shift> getShiftByShiftID(@RequestBody ShiftRequest request) {

        Shift shift = shiftService.getShiftByShiftID(request.getShiftID());
        if (shift != null) {
            return ResponseEntity.ok(shift);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/getShiftsByEmployeeID")
    public ResponseEntity<List<Shift>> getShiftsByEmployeeId(@RequestBody ShiftRequest request) {
        List<Shift> shifts = Arrays.asList(shiftService.getShiftByEmployeeId(request.getEmployeeID()));

        return ResponseEntity.ok(shifts);
    }

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
