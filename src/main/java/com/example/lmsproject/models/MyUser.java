package com.example.lmsproject.models;

// Import statements...

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Entity representing a user in the library management system, specifically for authentication.
 *
 * Spring Security requires an entity that implements UserDetails for authentication. In the context of this system, both `Student` and `Admin` can be authenticated.
 * Instead of making both entities implement UserDetails (which Spring Security doesn't support),
 * a separate entity `MyUser` is created which consolidates authentication details for both roles.
 *
 * The `MyUser` entity is therefore the central authentication entity, with separate tables for `Student` and `Admin` holding domain-specific information,
 * and their relationship with `MyUser` is represented through foreign keys (i.e., user_id).
 */
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyUser implements UserDetails, Serializable {

    // Delimiter used for splitting multiple authorities/roles stored in the authority field.
    private static final String AUTHORITY_DELIMITER = ",";

    // Primary key for the MyUser entity.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    // Unique username for the user, used in authentication.
    @Column(unique = true, nullable = false)
    private String username;

    // Password used for authentication.
    private String password;

    // String representation of user roles/authorities, delimited by AUTHORITY_DELIMITER.
    @Getter
    private String authority;

    // One-to-one relationship mapping between Student and MyUser.
    // This establishes a bidirectional relationship with the `myUser` field in the `Student` entity.
    @Getter
    @OneToOne(mappedBy = "myUser")
    @JsonIgnoreProperties("myUser")
    private Student student;

    // One-to-one relationship mapping between Admin and MyUser.
    // This establishes a bidirectional relationship with the `myUser` field in the `Admin` entity.
    @Getter
    @OneToOne(mappedBy = "myUser")
    @JsonIgnoreProperties("myUser")
    private Admin admin;

    // Convert the authority string into a collection of granted authorities for Spring Security.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] authorities = this.authority.split(AUTHORITY_DELIMITER);
        return Arrays.stream(authorities)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // Retrieves the password used for authentication.
    @Override
    public String getPassword() {
        return this.password;
    }

    // Retrieves the username used for authentication.
    @Override
    public String getUsername() {
        return this.username;
    }

    // Indicates whether the user's account has expired.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indicates whether the user is locked or unlocked.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indicates whether the user's credentials (password) has expired.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indicates whether the user is enabled or disabled.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
