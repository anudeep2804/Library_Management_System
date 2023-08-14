package com.example.lmsproject.services;

import com.example.lmsproject.models.MyUser;
import com.example.lmsproject.repositories.MyUserCacheRepository;
import com.example.lmsproject.repositories.MyUserRepository;
import com.example.lmsproject.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    @Value("${users.student.authority}")
    String studentAuthority;

    @Value("${users.admin.authority}")
    String admimAuthority;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = myUserCacheRepository.get(username);
        if(myUser == null){
            myUser = myUserRepository.findByUsername(username);
            if(myUser != null){
                myUserCacheRepository.set(myUser); // This call can be made parallel or async
            }
        }

        return myUser;
    }

    public MyUser createUser(UserCreateRequest userCreateRequest){

        MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent() != null){
            myUserBuilder.authority(studentAuthority);
        }else{
            myUserBuilder.authority(admimAuthority);
        }

        return myUserRepository.save(myUserBuilder.build());
    }
}