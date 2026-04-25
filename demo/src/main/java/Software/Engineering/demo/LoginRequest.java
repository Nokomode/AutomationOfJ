package Software.Engineering.demo;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

//This handles login requests. If the credentials are correct a response is returned, if not an error message is returned.
public class LoginRequest {
    private String employeeID;
    private String password;

    public LoginRequest() {}

    public String getEmployeeID() { return employeeID; }
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
