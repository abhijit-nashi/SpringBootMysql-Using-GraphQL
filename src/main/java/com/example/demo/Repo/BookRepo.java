package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
    // Custom queries can be added here if needed
}

