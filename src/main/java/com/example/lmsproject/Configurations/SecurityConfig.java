package com.example.lmsproject.Configurations;

import com.example.lmsproject.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security Configuration class for setting up authentication and authorization.
 * This configuration extends WebSecurityConfigurerAdapter to customize Spring Security's default configurations.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Dependency injection of custom User Details Service for authentication.
    @Autowired
    MyUserDetailsService myUserDetailsService;

    // Authority values extracted from the application's properties file for admin and student roles.
    @Value("${users.admin.authority}")
    String adminAuthority;

    @Value("${users.student.authority}")
    String studentAuthority;

    /**
     * Configuration for authentication using the custom User Details Service.
     *
     * @param auth AuthenticationManagerBuilder for constructing the authentication setup.
     * @throws Exception in case of authentication setup error.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    /**
     * Configuration for authorization rules, URL patterns and their required authorities.
     * Defines which roles can access which endpoints.
     *
     * @param http HttpSecurity to configure web based security for specific http requests.
     * @throws Exception in case of authorization setup error.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/student_for_admin").hasAuthority(adminAuthority) // Endpoint for admin operations on students
                .antMatchers(HttpMethod.POST, "/student/**").permitAll() // Allow POST requests to student endpoints for all users
                .antMatchers("/student/**", "/transaction/**").hasAuthority(studentAuthority) // Endpoints accessible only to students
                .antMatchers("/book/search/**").hasAnyAuthority(studentAuthority, adminAuthority) // Book search endpoint accessible to both students and admins
                .antMatchers("/book/**").hasAuthority(adminAuthority) // All other book endpoints exclusive to admins
                .antMatchers("/**").permitAll() // All other endpoints are public
                .and()
                .formLogin(); // Use form-based authentication
    }
}
