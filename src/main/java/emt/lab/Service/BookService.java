package emt.lab.Service;

import emt.lab.Model.Book;
import emt.lab.Model.Request.BookRequest;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long bookId);

    void addBook(BookRequest bookRequest);

    void editBook(Long bookId, BookRequest bookRequest);

    void deleteBook(Long bookId);
}
