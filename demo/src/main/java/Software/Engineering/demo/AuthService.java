package Software.Engineering.demo;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();

        if (encoder.matches(password, user.getPasswordHash())) {
            // Write code to create new shift with current time as start
            return user;
        } else {
            return null;
        }
    }

    public boolean register(String username, String password, String role, String firstName, String lastName, double salary, String phone, String email) {
        if (username == null || username.isBlank()) {
            return false;
        }
        if (password == null || password.isBlank()) {
            return false;
        }
        if (role == null || role.isBlank()) {
            return false;
        }
        if (firstName == null || firstName.isBlank()) {
            return false;
        }
        if (lastName == null || lastName.isBlank()) {
            return false;
        }
        if (salary < 11) {
            return false;
        }
        if (phone == null || phone.isBlank()) {
            return false;
        }
        if (email == null || email.isBlank()) {
            return false;
        }

        // Testing commit

        // Check password length
        if (password.length() < 8) {
            return false;
        }

        // Check role is valid
        List<String> validRoles = List.of("Manager", "Wait Staff", "Busboy", "Chef");
        if (!validRoles.contains(role)) {
            return false;
        }

        if (userRepository.existsByUsername(username)) {
            return false;
        }

        String hashedPassword = encoder.encode(password);

        User user = new User(username, hashedPassword, role, firstName, lastName, salary, phone, email);

        userRepository.save(user);

        return true;

    }

}
