package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Book;
import com.example.demo.Repo.BookRepo;



@Service
public class BookService {
@Autowired
private BookRepo bookrepo;


public List<Book> getALLBooks(){
    return bookrepo.findAll();
}

public Book getBookById(int id){
    return bookrepo.findById(id).orElseThrow(() -> new RuntimeException("No User Present"));
}

public void updateBook(int id, Book updatedBook) {
    bookrepo.findById(id).ifPresent(existingBook -> {
        existingBook.setName(updatedBook.getName());
        existingBook.setPrice(updatedBook.getPrice());
        bookrepo.save(existingBook);
    });
}

public Book saveBook(Book book){
    return bookrepo.save(book);
}

public void deleteBook(int id){
     bookrepo.deleteById(id);
}
}
