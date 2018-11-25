package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.domain.DepartmentRepository;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository brepository;

    @Autowired
    private DepartmentRepository drepository;

    @Test
    public void findByYearShouldReturnBook() {
        List<Book> books = brepository.findByYear(1984);
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getYear()).isEqualTo(2003);
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("Maa-artisokka, Milla", "Science for dummies", "45678765-4567", 1997, "7,80", drepository.findByName("Koti").get(0));
    	brepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    

}