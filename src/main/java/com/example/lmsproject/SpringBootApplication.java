package com.example.lmsproject;

import com.example.lmsproject.models.Admin;
import com.example.lmsproject.models.Author;
import com.example.lmsproject.models.MyUser;
import com.example.lmsproject.repositories.AdminRepository;
import com.example.lmsproject.repositories.AuthorRepository;
import com.example.lmsproject.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication implements CommandLineRunner {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	MyUserRepository myUserRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${users.admin.authority}")
	String admimAuthority;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("In run function of main class");
		List<Author> authorList =  authorRepository
				.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(30, "India", "P");

//		authorList.stream()
//				.map(author -> author.getBookList())
//				.forEach(books -> System.out.println(books.size()));

		authorList.forEach(System.out::println);

		MyUser myUser = MyUser.builder().username("adminuserlms").password(passwordEncoder.encode("admin123"))
				.authority(admimAuthority).build();

		myUser = myUserRepository.save(myUser);

		Admin admin = Admin.builder().age(40).name("Admin User").myUser(myUser).build();

		adminRepository.save(admin);
	}
}
