package com.example.lmsproject.request;

import com.example.lmsproject.models.Admin;
import com.example.lmsproject.models.Student;
import lombok.*;

/**
 * Represents a request to create a new user entity for the LMS system.
 * This class captures all the necessary fields required for user creation, whether they're a student or an admin.
 */
@Getter
@Setter
@NoArgsConstructor  // No-args constructor for creating an empty object.
@AllArgsConstructor // Constructor that initializes all fields.
@Builder            // Enables the use of the Builder pattern for creating objects.
public class UserCreateRequest {

    // Unique identifier (typically an email or a unique string) for the user.
    private String username;

    // Encrypted or plain-text password for the user.
    // Note: Ensure encryption at the service level before storing in the database.
    private String password;

    // Defines the role or access level of the user. e.g., "ROLE_ADMIN", "ROLE_STUDENT", etc.
    private String authority;

    // If the user is a student, this field captures their details.
    private Student student;

    // If the user is an admin, this field captures their details.
    private Admin admin;
}