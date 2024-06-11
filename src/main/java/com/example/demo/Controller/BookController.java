package com.example.demo.Controller;
import com.example.demo.Model.Book;
import com.example.demo.Repo.BookRepo;
import com.example.demo.Service.BookService;

import lombok.Data;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

@Controller
public class BookController {
 
@Autowired
private BookService bookservice;

@Autowired
private BookRepo bookRepo;

//@Autowired
// public BookController(BookService bookservice)
// {
//     this.bookservice=bookservice;
// }

@QueryMapping("allBooks")
public List<Book> getALLBooks()
{
    return bookservice.getALLBooks();
}

@QueryMapping("getBook")
public Book getBookById(@Argument int bookId)
{
    return bookservice.getBookById(bookId);

}

@MutationMapping("createBook")
public Book saveBook(@Argument BookInput book)
{
    Book b = new Book();
    b.setName(book.getName());
    b.setAuthor(book.getAuthor());
    b.setPrice(book.getPrice());
    return bookservice.saveBook(b);
}

@MutationMapping("updateBook")
public Book updateBook(@Argument int bookId, @Argument BookInput book) {
    //  bookservice.updateBook(id, updatedBook);
    //  return ResponseEntity.noContent().build();
     Book tobeupdatedBook = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
     tobeupdatedBook.setName(book.getName());
     tobeupdatedBook.setAuthor(book.getAuthor());
     tobeupdatedBook.setPrice(book.getPrice());
     bookRepo.save(tobeupdatedBook);
     return tobeupdatedBook;
}

@MutationMapping("deleteBook")
public Book deleteBook(@Argument int bookId)
{
    Book existingBook = bookRepo.findById(bookId).orElseThrow(()-> new RuntimeException("Book Not Found"));
    bookRepo.delete(existingBook);
    return existingBook;

}
}
@Data
class BookInput{
    private String name;
    private int price;
    private String author;

}