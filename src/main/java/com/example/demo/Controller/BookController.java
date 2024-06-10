package com.example.demo.Controller;
import com.example.demo.Model.Book;
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

@PutMapping("/{id}")
public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
     bookservice.updateBook(id, updatedBook);
     return ResponseEntity.noContent().build();
}

@MutationMapping("deleteBook")
public ResponseEntity<Void> deleteBook(@Argument int bookId)
{
    bookservice.deleteBook(bookId);
    return ResponseEntity.ok().build();

}
}
@Data
class BookInput{
    private String name;
    private int price;
    private String author;

}