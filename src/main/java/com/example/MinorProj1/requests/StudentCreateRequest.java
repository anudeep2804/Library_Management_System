package com.example.MinorProj1.requests;

import com.example.MinorProj1.models.AccountStatus;
import com.example.MinorProj1.models.Student;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StudentCreateRequest {



   @NotBlank
    private String studentname;

   @NotBlank
    private String contact;

   @NotBlank
   private String email;
   private String address;



    public Student to() {

        return Student.builder().contact(this.contact).studentname(this.studentname)
                .accountStatus(AccountStatus.ACTIVE).address(this.address)
                .email(this.email).build();
    }
}
