package Software.Engineering.demo;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /// searches the database for a user based on their first name
    public User findByFirstName(String name) {
        return userRepository.findByFirstName(name);
    }

    /// searches the database for a user based on their last name
    public User findByLastName(String name) {
        return userRepository.findByLastName(name);
    }

    /// returns a list of users by a role
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    /// returns all users/employees in the database
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /// finds an employee by their id
    public User findByEmployeeId(String username) {
        return userRepository.findById(username).orElse(null);
    }

    /// updates user information in the database
    public boolean updateUser(String id, String firstName, String lastName, String email, String phone, String role) {
        User user = userRepository.findById(id).orElse(null);

        System.out.println(id);
        if (user != null) {
            User newUser = new User(id, user.getPasswordHash(), role, firstName, lastName, user.getSalary(),phone, email);
            userRepository.save(newUser);
            return true;
        } else {
            return false;
        }
    }
}
