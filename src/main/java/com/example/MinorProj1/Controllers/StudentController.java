package com.example.MinorProj1.Controllers;

import com.example.MinorProj1.Services.StudentService;
import com.example.MinorProj1.requests.BookCreateRequest;
import com.example.MinorProj1.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController

public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void CreateStudent( @Valid @RequestBody StudentCreateRequest studentCreateRequest){
      studentService.create(studentCreateRequest);
    }
}
