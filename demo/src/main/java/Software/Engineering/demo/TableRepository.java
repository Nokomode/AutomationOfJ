package Software.Engineering.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<RTable, String> {
    /// Read as a sql query to search for a list of tables assigned to a certain employee
    List<RTable> findByEmployeeEmployeeId(String employeeID);
}

