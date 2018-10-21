package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book("Partanen, Selma", "Missä mikin on?", "2345-234-234", 2003, "13,50"));
			repository.save(new Book("Suojala, Sulevi Sinelmä", "Kuka sammutti valot Niilo?", "23-34-234", 2017, "26,10"));	
			repository.save(new Book("Raipakko, Pekka Pauliina", "Ajatusten parhaimmistoa..", "2346-876-3456-8", 1984, "8,00"));	
			repository.save(new Book("Tervaskanto, Alma", "Kotiruokaa", "76-225-457", 1704, "609,99"));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
