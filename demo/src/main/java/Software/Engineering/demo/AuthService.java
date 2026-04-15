package Software.Engineering.demo;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.LocalDateTime;
import java.time.LocalDate;
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

    public User login(String username, String password) {
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();

        if (encoder.matches(password, user.getPasswordHash())) {

            //When the employee Logs in they will automatically  be clocked in and start their shift. Here is where that will be implemented. We will also need to add a clock out function when the employee logs out.
            LocalDateTime startTime = LocalDateTime.now();
            ShiftStart.setStartTime(startTime);
            Shift newShift = new Shift(startTime, LocalDateTime.now(), user, LocalDate.now());
            shiftRepository.save(newShift);


            return user;
        } else {
            return null;
        }
    }


    //This will find the user that's logging out/clocking out current shift
    public void logout(String username) {
        List<Shift> activeShiftOpt = shiftRepository.findByEmployeeEmployeeID(username);

        //Loop through the list and find the shift with the same start time
        LocalDateTime start = ShiftStart.startTime;
        for (Shift shift : activeShiftOpt) {
            if (shift.getStartTime() == start ) {
                shiftRepository.save(new Shift(LocalDateTime.now(), shift.getShiftId()));
            }
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

        if (userRepository.existsById(username)) {
            return false;
        }

        String hashedPassword = encoder.encode(password);

        User user = new User(username, hashedPassword, role, firstName, lastName, salary, phone, email);

        userRepository.save(user);

        return true;

    }

}
