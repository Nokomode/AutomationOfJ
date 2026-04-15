package Software.Engineering.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // optional
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // make login public
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable()); // or formLogin() if you want a form
        return http.build();
    }

    @Bean
    public UserDetailsService users(UserRepository userRepo) {
        return username -> {
            Software.Engineering.demo.User user = userRepo.findById(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmployeeID())
                    .password(user.getPasswordHash()) // your hashed password column
                    .authorities(user.getRole())           // or authorities if you have more complex roles
                    .build();
        };
    }
}