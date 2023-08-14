package com.example.lmsproject.Controllers;

import com.example.lmsproject.models.MyUser;
import com.example.lmsproject.models.Student;
import com.example.lmsproject.request.StudentCreateRequest;
import com.example.lmsproject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        studentService.create(studentCreateRequest);
    }

    // only for students
    @GetMapping("/student")
    public Student getStundet() throws Exception {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser)authentication.getPrincipal();
        if(myUser.getStudent()==null){
            throw new Exception("User is not a Student");
        }
        myUser.getStudent().getId();
        int studentId = myUser.getStudent().getId();
        return studentService.findStudentByStudentId(studentId);
    }

    // only for admins
    @GetMapping("/student_for_admin")
    public Student getStudentForAdmin(@RequestParam("studentId") int studentId) throws Exception {
        // check the person accessing this API is admin or not
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myuser = (MyUser) authentication.getPrincipal();
        if(myuser.getAdmin()==null){
            throw new Exception(" User is not admin");
        }

        return studentService.findStudentByStudentId(studentId);
    }
}
