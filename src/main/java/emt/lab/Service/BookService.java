package emt.lab.Service;

import emt.lab.Enum.Category;
import emt.lab.Model.Book;
import emt.lab.Model.Request.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Page<Book> findAllPageable(Pageable pageable);

    Optional<Book> findById(Long bookId);

    Optional<Book> addBook(BookDto bookRequest);

    Optional<Book> editBook(Long bookId, BookDto bookRequest);

    void deleteBook(Long bookId);

    Optional<Book> markBookAsTaken(Long bookId);

    List<Category> findAllBookCategories();
}
