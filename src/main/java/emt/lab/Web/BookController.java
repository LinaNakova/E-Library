package emt.lab.Web;

import emt.lab.Model.Book;
import emt.lab.Model.Request.BookRequest;
import emt.lab.Service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return this.bookService.findById(id);
    }

    @PostMapping("/add-book")
    public void addBook(@RequestBody BookRequest bookRequest) {
        this.bookService.addBook(bookRequest);
    }

    @PutMapping("/edit-book/{id}")
    public void addBook(@RequestBody BookRequest bookRequest, @PathVariable Long id) {
        this.bookService.editBook(id, bookRequest);
    }

    @DeleteMapping("/delete-book/{id}")
    public void deleteBook(@PathVariable Long id) {
        this.bookService.deleteBook(id);
    }
}
