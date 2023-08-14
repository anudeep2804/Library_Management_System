package com.example.lmsproject;

import com.example.lmsproject.models.Author;
import com.example.lmsproject.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.List;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication implements CommandLineRunner {

	/* Note : Autowire never fits with static keyword , it will throw nullpointerexception */

	@Autowired
	AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

	@Override // this commandliner will run all call methods as soon as server starts
	public void run(String... args) throws Exception {
		System.out.println("Indicating we can instantly call methods as soon as sever started using commandliner");
		List<Author> authorList = authorRepository.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(30,"India","p");
		//authorList.stream().forEach(x -> System.out.println(x.getName()));
		authorList.forEach(System.out::println); // this will initially throw an error as fetch type for booklist is by default lazy
	}
}
