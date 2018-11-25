package com.example.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=1, max=150)
    private String author, title;
    @NotNull
    @Size(min=13, max=13)
    private String isbn;
    @NotNull
    @Size(min=1, max=6)
    private String price;
    @NotNull
    @Min(4)
    private int year;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "departmentid")
    private Department department;

    public Book() {}
	 
	public Book(String author, String title, String isbn, int year, String price, Department department) {
		super();
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.year = year;
		this.price = price;
		this.department = department;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		if (this.department != null)
		return "Book [id=\" + id + \", author=" + author + ", title=\" + title + \", isbn=\" + isbn + \", year=\" + year + \", price=\" + price + \" department =\" + this.getDepartment() + \"]";
		else
		return "Book [id=\" + id + \", author=" + author + ", title=\" + title + \", isbn=\" + isbn + \", year=\" + year + \", price=\" + price + \"]";
	}
	
}
