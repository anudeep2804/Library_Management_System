package com.example.lmsproject.controllers;

import com.example.lmsproject.models.MyUser;
import com.example.lmsproject.models.Student;
import com.example.lmsproject.request.StudentCreateRequest;
import com.example.lmsproject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/** This controller handles HTTP requests related to Student operations. */
@RestController
public class StudentController {

    // Service for interacting with Student related data.
    @Autowired
    StudentService studentService;

    /** Endpoint to create a new student. Expects a valid request body with student creation details.
     * @param studentCreateRequest The request payload containing student details.
     */
    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        studentService.create(studentCreateRequest);
    }

    /** Endpoint to fetch student details for the logged in student.
     * @return Student Details of the logged-in student.
     * @throws Exception if the logged-in user is not a student.
     */
    @GetMapping("/student")
    public Student getStundet() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser)authentication.getPrincipal();
        if(myUser.getStudent() == null){
            throw new Exception("User is not a Student");
        }
        myUser.getStudent().getId();
        int studentId = myUser.getStudent().getId();
        return studentService.findStudentByStudentId(studentId);
    }

    /** Endpoint to fetch student details for admins.
     * @param studentId The ID of the student to retrieve.
     * @return Student Details of the specified student.
     * @throws Exception if the logged-in user is not an admin.
     */
    @GetMapping("/student_for_admin")
    public Student getStudentForAdmin(@RequestParam("studentId") int studentId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myuser = (MyUser) authentication.getPrincipal();
        if(myuser.getAdmin() == null){
            throw new Exception(" User is not admin");
        }
        return studentService.findStudentByStudentId(studentId);
    }
}
