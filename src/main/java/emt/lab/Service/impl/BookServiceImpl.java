package emt.lab.Service.impl;

import emt.lab.Enum.Category;
import emt.lab.Exception.InvalidBookIdException;
import emt.lab.Model.Author;
import emt.lab.Model.Book;
import emt.lab.Model.Request.BookRequest;
import emt.lab.Repository.BookRepository;
import emt.lab.Service.AuthorService;
import emt.lab.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(Long bookId) {
        return this.bookRepository.findById(bookId).orElseThrow(() -> new InvalidBookIdException(bookId));
    }

    @Override
    public void addBook(BookRequest bookRequest) {
        List<Author> bookAuthors = this.authorService.findByIds(bookRequest.authorIds);
        Book book = new Book(bookRequest.name, Category.valueOf(bookRequest.category), bookAuthors, bookRequest.availableCopies);
        this.bookRepository.save(book);
    }

    @Override
    public void editBook(Long bookId, BookRequest bookRequest) {
        Book book = this.findById(bookId);
        book.setName(bookRequest.name);
        book.setCategory(Category.valueOf(bookRequest.category));
        book.setAvailableCopies(bookRequest.availableCopies);
        List<Author> bookAuthors = this.authorService.findByIds(bookRequest.authorIds);
        book.setAuthors(bookAuthors);
        this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = this.findById(bookId);
        this.bookRepository.delete(book);
    }
}
