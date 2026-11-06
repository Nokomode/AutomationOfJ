package Software.Engineering.demo;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final ShiftRepository shiftRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, ShiftRepository shiftRepository) {
        this.userRepository = userRepository;
        this.shiftRepository = shiftRepository;
    }
    /// Gets login information from the frontend and attempts to call login service to perform the login
    public User login(String username, String password) {
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();

        if (encoder.matches(password, user.getPasswordHash())) {

            //When the employee Logs in they will automatically  be clocked in and start their shift. Here is where that will be implemented. We will also need to add a clock out function when the employee logs out.
            LocalTime startTime = LocalTime.now();
            ShiftStart.setStartTime(startTime);
            Shift newShift = new Shift(startTime, LocalTime.now(), user, LocalDate.now());
            shiftRepository.save(newShift);


            return user;
        } else {
            return null;
        }
    }


    ///This will find the user that's logging out/clocking out current shift
    public boolean logout(String username) {
        List<Shift> activeShiftOpt = shiftRepository.findByEmployeeEmployeeId(username);
        System.out.println("Search for: " + username);
        //Loop through the list and find the shift with the same start time
        for (Shift shift : activeShiftOpt) {
            System.out.println(shift.getStartTime());
            System.out.println(ShiftStart.startTime.truncatedTo(ChronoUnit.SECONDS));
            System.out.println("\n");
            if (shift.getStartTime().truncatedTo(ChronoUnit.MINUTES)
                    .equals(ShiftStart.startTime.truncatedTo(ChronoUnit.MINUTES)) ) {
                shift.setEndTime(LocalTime.now());
                shiftRepository.save(shift);
                return true;
            }
        }

        return false;
    }

    /// Attempts to register a user, returns false if failed such as a username already exists
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


        /// Check password length
        if (password.length() < 8) {
            return false;
        }

        /// Check role is valid
        List<String> validRoles = List.of("Manager", "Wait Staff", "Busboy", "Chef");
        if (!validRoles.contains(role)) {
            return false;
        }

        if (userRepository.existsById(username)) {
            return false;
        }

        String hashedPassword = encoder.encode(password);

        User user = new User(username, hashedPassword, role, firstName, lastName, salary, phone, email);

        userRepository.save(user);

        return true;

    }

}
