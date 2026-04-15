package Software.Engineering.demo;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;
import java.util.*;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer>{
    Optional<Shift> findById(int id);
    List<Shift> findByEmployeeId(String id);
}

