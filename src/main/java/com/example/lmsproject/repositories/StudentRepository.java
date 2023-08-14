package com.example.lmsproject.repositories;

import com.example.lmsproject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository  -- Not mandatory because SimpleJPARepository class has a repository annotation


/**
 * Repository interface for CRUD operations related to the Book entity.
 * This interface extends JpaRepository which provides JPA related methods
 * that can be used with the Student entity.
 */

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
