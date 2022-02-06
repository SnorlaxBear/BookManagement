package com.huan.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huan.demo.model.Book;
import com.huan.demo.repo.BookRepository;

@Service
public class BookService {

	private final BookRepository repository;

	public BookService(@Autowired final BookRepository repository) {
		this.repository = repository;
	}

	public List<Book> findAll() {
		return repository.findAll();
	}

	public Optional<Book> findOne(Long id) {
		Optional<Book> book = repository.findById(id);
		return book;
	}

	public void save(Book book) {
		repository.save(book);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
