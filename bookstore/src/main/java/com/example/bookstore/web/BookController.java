package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.DepartmentRepository;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller

public class BookController {   
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String home() {
    	return "index";
	}

	@Autowired
	private BookRepository brepository; 
	
	@Autowired
	private DepartmentRepository drepository; 

    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", brepository.findAll());
        return "booklist";
    }
  
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("departments", drepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
        	return "addbook";
        }
        
        brepository.save(book);
        return "redirect:booklist";
    }     

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.deleteById(bookId);
        return "redirect:../booklist";
    }     

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String addBook(@PathVariable("id") Long bookId, Model model) {
    	model.addAttribute("book",brepository.findById(bookId));
    	model.addAttribute("departments",drepository.findAll());
        return "editbook";
    }
	// RESTful service to get all books
    // http://localhost:8080/books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) brepository.findAll();
    }    

	// RESTful service to get book by id
    // http://localhost:8080/book/4
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return brepository.findById(bookId);
    }       
	@RequestMapping(value={"/", "/login"})
	public String login() {
    	return "login";
	}
    
}
