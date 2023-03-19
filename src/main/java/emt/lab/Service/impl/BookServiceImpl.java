package emt.lab.Service.impl;

import emt.lab.Enum.Category;
import emt.lab.Exception.InvalidBookIdException;
import emt.lab.Model.Author;
import emt.lab.Model.Book;
import emt.lab.Model.Request.BookDto;
import emt.lab.Repository.BookRepository;
import emt.lab.Service.AuthorService;
import emt.lab.Service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Page<Book> findAllPageable(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return this.bookRepository.findById(bookId);
    }

    @Override
    public Optional<Book> addBook(BookDto bookRequest) {
        List<Author> bookAuthors = this.authorService.findByIds(bookRequest.authorIds);
        Book book = new Book(bookRequest.name, Category.valueOf(bookRequest.category), bookAuthors, bookRequest.availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> editBook(Long bookId, BookDto bookRequest) {
        Book book = this.findById(bookId)
                .orElseThrow(() -> new InvalidBookIdException(bookId));
        book.setName(bookRequest.name);
        book.setCategory(Category.valueOf(bookRequest.category));
        book.setAvailableCopies(bookRequest.availableCopies);
        List<Author> bookAuthors = this.authorService.findByIds(bookRequest.authorIds);
        book.setAuthors(bookAuthors);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = this.findById(bookId)
                .orElseThrow(() -> new InvalidBookIdException(bookId));
        this.bookRepository.delete(book);
    }

    @Override
    public Optional<Book> markBookAsTaken(Long bookId) {
        Book book = this.findById(bookId)
                .orElseThrow(() -> new InvalidBookIdException(bookId));
        if (book.getAvailableCopies() != 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookRepository.save(book);
            return Optional.of(book);
        } else
            return Optional.empty();
    }

    @Override
    public List<Category> findAllBookCategories() {
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}
