package com.example.lmsproject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity representing an Admin in the library management system.
 * The Admin entity provides additional details about an admin user,
 * like their name and age. The Admin is also linked to a User entity,
 * which holds authentication and other user-specific data.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin implements Serializable {

    // Primary key for the Admin entity.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Name of the admin.
    private String name;

    // Age of the admin.
    private int age;

    // Timestamp capturing when the admin entity was created.
    @CreationTimestamp
    private Date createdOn;

    // Timestamp capturing the last time the admin entity was updated.
    @UpdateTimestamp
    private Date updatedOn;


    // Relationship mapping to represent the associated user details for this admin.
    // The relationship is one-to-one since an Admin corresponds to a single User.
    // The JsonIgnoreProperties annotation ensures that when the Admin object is serialized to JSON,
    //the "student", "admin", and "password" properties of the associated MyUser entity are not included in the output.
    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"student", "admin", "password"})
    private MyUser myUser;
}
