import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;



/* ---------- ENTITY ---------- */

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    private String role;

    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role;}
}



/* ---------- LOGIN REQUEST DTO ---------- */

class LoginRequest {
    private String username;
    private String password;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}



/* ---------- REPOSITORY ---------- */

@Repository
interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}



/* ---------- SERVICE ---------- */

@Service
class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        return encoder.matches(password, user.getPasswordHash());
    }
}



/* ---------- CONTROLLER ---------- */

@RestController
@RequestMapping("/api/auth")
class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {

        boolean success = authService.login(
            request.getUsername(),
            request.getPassword()
        );

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}

