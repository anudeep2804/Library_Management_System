package com.example.lmsproject.request;

import com.example.lmsproject.models.*;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Represents a request to create a new Student entity.
 * The class captures all the necessary fields required for creating a student.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotBlank  // Ensures the name field is neither null nor just whitespace.
    private String name;

    @NotBlank  // Ensures the contact field is neither null nor just whitespace.
    private String contact;

    private String address;
    private String email;

    @NotBlank  // Ensures the username field is neither null nor just whitespace.
    private String username;

    @NotBlank  // Ensures the password field is neither null nor just whitespace.
    private String password;

    /**
     * Converts the StudentCreateRequest object to a Student entity.
     *
     * @return A new Student entity populated with fields from the request.
     */
    public Student to() {
        return Student.builder()
                .address(address)
                .contact(contact)
                .email(email)
                .name(name)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    /**
     * Converts the StudentCreateRequest object to a UserCreateRequest object.
     * This is useful if the system requires a user creation request alongside a student creation request.
     *
     * @return A new UserCreateRequest populated with fields from the request and associated Student entity.
     */
    public UserCreateRequest toUser() {
        return UserCreateRequest.builder()
                .student(to())
                .username(username)
                .password(password)
                .build();
    }
}