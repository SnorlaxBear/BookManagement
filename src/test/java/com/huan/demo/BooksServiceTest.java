package com.huan.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.huan.demo.model.Book;
import com.huan.demo.repo.BookRepository;
import com.huan.demo.service.BookService;

public class BooksServiceTest {

	@Mock
	private BookRepository bookRepository;

	private BookService bookService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		bookService = new BookService(bookRepository);
	}

	@Test
	public void testFindOneNothing() {
		when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

		assertFalse(bookService.findOne(1L).isPresent());

		verify(bookRepository).findById(1L);
	}

	@Test
	public void testFindOne() {
		when(bookRepository.findById(5L)).thenReturn(Optional.of(new Book("name", "author", "isbn-123")));

		final Optional<Book> bookOptional = bookService.findOne(5L);

		assertTrue(bookOptional.isPresent());
		assertEquals(bookOptional.get().getName(), "name");
		assertEquals(bookOptional.get().getAuthor(), "author");
		assertEquals(bookOptional.get().getISBN(), "isbn-123");
	}

	@Test
	public void testCreateBook() {
		Book book = new Book("name", "author", "isbn-123");
		bookService.save(book);
		verify(bookRepository).save(book);
	}

	@Test
	public void testDelete() {
		bookService.delete(1L);
		verify(bookRepository).deleteById(1L);
	}

}
