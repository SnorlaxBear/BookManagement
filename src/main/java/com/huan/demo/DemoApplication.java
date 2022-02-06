package com.huan.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.huan.demo.model.Book;
import com.huan.demo.repo.BookRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// init bean to insert 3 books into h2 database.
	@Bean
	CommandLineRunner initDatabase(BookRepository repository) {
		return args -> {
			repository.save(new Book("Spring Boot in Action", "Craig Walls", "978-1617292545"));
			repository.save(new Book("Intro to Java Programming", "Daniel Liang", "978-0134611037"));
			repository.save(new Book("Cracking the Coding Interview", "Gayler Laakmann McDowell", "978-0984782857"));
		};
	}

}
