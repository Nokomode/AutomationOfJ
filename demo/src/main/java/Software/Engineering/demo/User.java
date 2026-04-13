package Software.Engineering.demo;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;


@Entity
@Table(name = "users")
public class User {

    public User() {

    }

    public User(String username, String password, String role, String firstName, String lastName, double salary, String phone, String email ) {
        this.username = username;
        this.passwordHash = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "role")
    private String role;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Salary")
    private double salary;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;



    public Integer getId() {return id;}
    public String getUsername() {return username;}
    public String getRole() {return role;}
    public String getPasswordHash() {return passwordHash;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public double getSalary() {return salary;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
}
