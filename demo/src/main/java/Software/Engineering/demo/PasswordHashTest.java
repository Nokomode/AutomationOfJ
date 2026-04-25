package Software.Engineering.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//This class will take a raw password and transform it into a secure hash.
public class PasswordHashTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String hash = encoder.encode(rawPassword);
        System.out.println(hash);
    }
}