package com.huan.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huan.demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
