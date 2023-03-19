package emt.lab.Web;

import emt.lab.Enum.Category;
import emt.lab.Model.Book;
import emt.lab.Model.Request.BookDto;
import emt.lab.Service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return this.bookService.findAll();
    }

    @GetMapping("/pageable")
    public Page<Book> getAllBooksPageable(Pageable pageable) {
        return this.bookService.findAllPageable(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookRequest) {
        return this.bookService.addBook(bookRequest)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookRequest, @PathVariable Long id) {
        return this.bookService.editBook(id, bookRequest)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        this.bookService.deleteBook(id);
        if (this.bookService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/mark-as-taken/{id}")
    public ResponseEntity markBookAsTaken(@PathVariable Long id) {
        if (this.bookService.markBookAsTaken(id).isEmpty())
            return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok().build();

    }

    @GetMapping("/categories")
    public List<Category> findAllCategories() {
        return this.bookService.findAllBookCategories();
    }
}
