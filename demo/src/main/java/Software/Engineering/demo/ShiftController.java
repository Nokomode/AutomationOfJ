package Software.Engineering.demo;

import java.time.LocalDateTime;
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
    /// sends a shift to the frontend by its shift id, sends a not found error to the frontend if the search fails
    @PostMapping("/getByShiftID")
    public ResponseEntity<Shift> getShiftByShiftID(@RequestBody ShiftRequest request) {

        Shift shift = shiftService.getShiftByShiftID(request.getShiftID());
        if (shift != null) {
            return ResponseEntity.ok(shift);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    /// sends a list of shifts to the frontend by their associated employee id, sends a 401 error wtha null body if the search fails
    @GetMapping("/getShiftsByEmployeeID/{id}")
    public ResponseEntity<List<Shift>> getShiftsByEmployeeId(@PathVariable String id) {
        List<Shift> shifts = shiftService.getShiftByEmployeeId(id);
        System.out.println(shifts);
        if (shifts != null) {
            return ResponseEntity.ok(shifts);
        } else {
            return ResponseEntity.status(401).body(null);
        }

    }

    /// sends all shifts to the frontend
    @PostMapping("/getAllShifts")
    public ResponseEntity<List<Shift>> getAllShifts() {

        List<Shift> shifts = shiftService.getAllShifts();
        return ResponseEntity.ok(shifts);
    }



    /* @PostMapping("/endShift/{id}")
    public ResponseEntity<String> endShift(@PathVariable String id) {
       LocalDateTime endTime = LocalDateTime.now();
       shiftService.endShift(id, endTime);
       return ResponseEntity.ok("Shift added successfully");
    } */

}
