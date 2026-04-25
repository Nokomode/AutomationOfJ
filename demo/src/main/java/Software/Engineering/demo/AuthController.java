package Software.Engineering.demo;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    //Constructor injection for AuthService
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //This function handles login requests. It takes a LoginRequest object as input, which contains the employee ID and password. It authenticates the user and returns a login response if sucessful, or an error message if incorrect.
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

    //This function handles registration requests. It takes a RegisterRequest object as input, which contains the employee ID, password, role, first name, last name, salary, phone number, and email. If sucessful the account is created, if there is a conflict an error message is returned.
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

    @GetMapping("/test")
    public String test() {
        return "Hello, world!";
    }
}
