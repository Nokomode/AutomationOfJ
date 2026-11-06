package Software.Engineering.demo;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /// Gets login information from the frontend then sends a success response back if the user information was correct
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User success = authService.login(request.getEmployeeID(), request.getPassword());
        System.out.println("Received login: " + request.getEmployeeID());

        if (success != null) {
            LoginResponse response = new LoginResponse();
            response.setUsername(success.getEmployeeID());
            response.setRole(success.getRole());
            response.setFirstName(success.getFirstName());
            response.setLastName(success.getLastName());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    /// Gets registration information from the frontend then sends a success response back if the registration was successful (user didn't already exist)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        boolean success = authService.register(request.getEmployeeID(), request.getPassword(), request.getRole(), request.getFirstName(), request.getLastName(), request.getSalary(), request.getPhone(), request.getEmail());
        System.out.println("Received registration: " + request.getEmployeeID());

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username already exists");
        }

    }

    /// Gets the username of the current user on the frontend and calls AuthService.logout to log the user out. Sends a success response upon successful logout.
    @GetMapping("/logout/{username}")
    public ResponseEntity<String> logout(@PathVariable String username) {
        boolean success = authService.logout(username);
        System.out.println("Logging out: " + username);
        if (success) {
            return ResponseEntity.status(200).body("Logged out successfully");

        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Could not logout");
        }
    }

}
