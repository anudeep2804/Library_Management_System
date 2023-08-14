package com.example.lmsproject.repositories;

import com.example.lmsproject.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for CRUD operations related to the Book entity.
 * This interface extends JpaRepository which provides JPA related methods
 * that can be used with the MyUser entity.
 */

public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    MyUser  findByUsername(String username);
}
