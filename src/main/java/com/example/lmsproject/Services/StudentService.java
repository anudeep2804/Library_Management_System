package com.example.lmsproject.services;

import com.example.lmsproject.models.MyUser;
import com.example.lmsproject.models.Student;
import com.example.lmsproject.repositories.StudentRepository;
import com.example.lmsproject.request.StudentCreateRequest;
import com.example.lmsproject.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Value("${users.student.authority}")
    String studentAuthority;

    @Autowired
    MyUserDetailsService userDetailsService;

    public void create(StudentCreateRequest studentCreateRequest){

        UserCreateRequest userCreateRequest = studentCreateRequest.toUser();
        MyUser myuser =  userDetailsService.createUser(userCreateRequest);
        Student student = studentCreateRequest.to();
        student.setMyUser(myuser);
        studentRepository.save(student);
    }

    public Student findStudentByStudentId(int sId){
        return studentRepository
                .findById(sId).orElse(null);
    }

}
