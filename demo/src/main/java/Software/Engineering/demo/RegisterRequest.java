package Software.Engineering.demo;

public class RegisterRequest {
    private String employeeID;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private double salary;
    private String phone;
    private String email;

    public RegisterRequest() {}


    public String getEmployeeID() { return employeeID; }
    public void setEmployeeID(String username) { this.employeeID = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
