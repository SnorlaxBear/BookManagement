package com.huan.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.huan.demo.model.Book;
import com.huan.demo.repo.BookRepository;
import com.huan.demo.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public List<Book> findAll() {
		return bookService.findAll();
	}

	@PostMapping("/books")
	ResponseEntity<String> createBook(@RequestBody Book newBook) throws IOException {
		bookService.save(newBook);
		return ResponseEntity.status(HttpStatus.CREATED).body("Book created");

	}

	@GetMapping("/books/{id}")
	Optional<Book> findBook(@PathVariable Long id) throws IOException {
		return bookService.findOne(id);
	}

	@PutMapping("/books/{id}")
	ResponseEntity<String> updateBook(@RequestBody Book updatedBook, @PathVariable Long id) {
		final Optional<Book> bookOptional = bookService.findOne(id);
		if (bookOptional.isPresent()) {
			final Book book = bookOptional.get();
			book.setAuthor(updatedBook.getAuthor());
			book.setName(updatedBook.getName());
			book.setISBN(updatedBook.getISBN());

			bookService.save(book);

			return ResponseEntity.ok(book.toString());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book found");

	}

	@DeleteMapping("/books/{id}")
	ResponseEntity<String> deleteBook(@PathVariable Long id) {
		final Optional<Book> bookOptional = bookService.findOne(id);
		if (bookOptional.isPresent()) {
			final Book book = bookOptional.get();
			bookService.delete(id);
			return ResponseEntity.ok("book has been deleted");

		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book found");
	}

}
