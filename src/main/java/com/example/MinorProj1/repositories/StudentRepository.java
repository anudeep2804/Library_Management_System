package com.example.MinorProj1.repositories;

import com.example.MinorProj1.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository is not mandatory here because simpleJPARepository class has Reporistory annotation

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
