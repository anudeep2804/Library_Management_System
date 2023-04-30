package com.example.MinorProj1.Services;

import com.example.MinorProj1.models.Student;
import com.example.MinorProj1.repositories.StudentRepository;
import com.example.MinorProj1.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StudentService {

    @Autowired
    StudentRepository studentRepository;
 public void create (StudentCreateRequest studentCreateRequest){
    Student student= studentCreateRequest.to();
    studentRepository.save(student);

 }
    public Student findByStundetId(int studentid){

        return studentRepository.findById(studentid).orElse(null);
    }
}
