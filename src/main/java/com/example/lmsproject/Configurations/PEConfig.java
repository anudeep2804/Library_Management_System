package com.example.lmsproject.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class to provide password encoding functionalities.
 * This configuration sets up a bean for encoding passwords using the BCrypt algorithm.
 * It ensures that passwords stored in the system are securely hashed.
 */
@Configuration
public class PEConfig {

    /**
     * Provides a PasswordEncoder bean that uses the BCrypt hashing algorithm.
     * The BCrypt algorithm automatically generates a salt, ensuring each hashed password is unique.
     * This bean can be autowired wherever password encoding/decoding functionalities are required.
     *
     * @return A PasswordEncoder instance configured with BCrypt.
     */
    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
