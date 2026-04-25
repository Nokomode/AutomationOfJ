package Software.Engineering.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Custom repository that will handle the user's requests in the database.
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String username);
}
