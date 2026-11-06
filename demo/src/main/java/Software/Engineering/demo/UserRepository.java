package Software.Engineering.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // checks whether a user exists in the database by their username
    boolean existsById(String username);

    // Generates sql queries to search for users by their first name, last name, or role
    User findByFirstName(String name);
    User findByLastName(String name);
    List<User> findByRole(String role);
}
