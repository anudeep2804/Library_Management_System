package com.example.lmsproject.repositories;

import com.example.lmsproject.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
