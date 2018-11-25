package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Department;
import com.example.bookstore.domain.DepartmentRepository;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, DepartmentRepository drepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			drepository.save(new Department("Kauhu"));
			drepository.save(new Department("Koti"));
			drepository.save(new Department("Ei lainata"));

			brepository.save(new Book("Partanen, Selma", "Missä mikin on?", "2345394860143", 2003, "13,50", drepository.findByName("Ei lainata").get(0)));
			brepository.save(new Book("Suojala, Sulevi Sinelmä", "Kuka sammutti valot Niilo?", "9455738345672", 2017, "26,10", drepository.findByName("Kauhu").get(0)));	
			brepository.save(new Book("Raipakko, Pekka Pauliina", "Ajatusten parhaimmistoa..", "1234687634568", 1984, "8,00", drepository.findByName("Ei lainata").get(0)));	
			brepository.save(new Book("Tervaskanto, Alma", "Kotiruokaa", "4458797762254", 1704, "609,99", drepository.findByName("Koti").get(0)));	

			// Create users: admin/admin user/user
			log.info("create users");
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
