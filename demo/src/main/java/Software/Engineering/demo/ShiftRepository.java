package Software.Engineering.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.*;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer>{
    Optional<Shift> findById(int id);
    List<Shift> findByEmployeeEmployeeID(String id);
}

