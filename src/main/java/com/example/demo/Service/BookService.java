package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Book;
import com.example.demo.Repo.BookRepo;



@Service
public class BookService {

private BookRepo bookrepo;

@Autowired
public void BookService(BookRepo bookrepo) 
{
    this.bookrepo = bookrepo;
}

public List<Book> getALLBooks(){
    return bookrepo.findAll();
}

public Optional<Book> getBookById(Long id){
    return bookrepo.findById(id);
}

public Book saveBook(Book book){
    return bookrepo.save(book);
}

public void deleteBook(Long id){
     bookrepo.deleteById(id);
}
}
