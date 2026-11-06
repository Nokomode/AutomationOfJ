package Software.Engineering.demo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;


@Entity
@Table(name = "employee")
public class User {

    public User() {

    }

    public User(String username, String password, String role, String firstName, String lastName, double salary, String phone, String email ) {
        this.employeeId = username;
        this.passwordHash = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
    }

    public User(String username, String role, String firstName, String lastName, String phone, String email ) {
        this.employeeId = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    @Id
    @Column(name = "Employee_ID")
    private String employeeId;

    @Column(name = "Password")
    private String passwordHash;

    @Column(name = "Role")
    private String role;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Salary")
    private double salary;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone#")
    private String phone;


    /// getters and setters
    public String getEmployeeID() {return employeeId;}
    public String getRole() {return role;}
    public String getPasswordHash() {return passwordHash;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public double getSalary() {return salary;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
}
