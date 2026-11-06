package Software.Engineering.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.*;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer>{
    ///  Doesnt necessarily need to be defined, but searches the database for a a shift based on the primary key, the shiftid
    Optional<Shift> findById(int id);
    ///  Runs as a SQL query that Searches for a list of shifts based on an employee Id
    List<Shift> findByEmployeeEmployeeId(String id);
}