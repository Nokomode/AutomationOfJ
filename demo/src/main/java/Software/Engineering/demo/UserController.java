package Software.Engineering.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    /// Sends a user to the frontend based on querying their first name
    @GetMapping("/getUserByFirstName/{firstName}")
    public ResponseEntity<User> findByFirstName(@PathVariable String firstName) {
        User user = userService.findByFirstName(firstName);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends a user to the frontend based on querying their last name
    @GetMapping("/findByLastName/{name}")
    public ResponseEntity<User> findByLastName(@PathVariable String name) {
        User user = userService.findByLastName(name);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    /// Sends a user to the frontend based on querying their role
    @GetMapping("/getUserByRole/{role}")
    public ResponseEntity<List<User>> findByRole(@PathVariable String role) {
        List<User> users = userService.findByRole(role);

        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends all users to the frontend, sends not found if the query fails for some reason
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Sends a user to the frontend based on querying their employeeId
    @GetMapping("/getUserByEmployeeId/{id}")
    public ResponseEntity<User> findByEmployeeId(@PathVariable String id) {
        User user = userService.findByEmployeeId(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /// Updates user information, sends a not found error to the frontend if the update fails
    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserRequest req) {
        boolean success = userService.updateUser(req.getEmployeeId(), req.getFirstName(), req.getLastName(), req.getEmail(), req.getPhone(), req.getRole());

        if (success) {
            return ResponseEntity.ok("User updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed to update user");
        }
    }
}
