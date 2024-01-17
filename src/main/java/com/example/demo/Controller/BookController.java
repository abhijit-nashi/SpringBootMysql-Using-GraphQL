package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Book;
import com.example.demo.Service.BookService;

import java.util.List;
import org.springframework.http.HttpStatus;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class BookController {
    
private BookService bookservice;

//@Autowired
public BookController(BookService bookservice)
{
    this.bookservice=bookservice;
}


@GetMapping
public List<Book> getALLBooks()
{
    return bookservice.getALLBooks();
}

@GetMapping("/{id}")
public ResponseEntity<Book> getBookById(@PathVariable Long id)
{
    return bookservice.getBookById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

}

@PostMapping
public ResponseEntity<Book> saveBook(@RequestBody Book book)
{
    Book addedbook = bookservice.saveBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(addedbook);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteBook(@PathVariable Long id)
{
    bookservice.deleteBook(id);
    return ResponseEntity.ok().build();

}
}
