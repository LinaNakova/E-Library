package emt.lab.Service.impl;

import emt.lab.Exception.InvalidAuthorIdException;
import emt.lab.Model.Author;
import emt.lab.Repository.AuthorRepository;
import emt.lab.Service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long authorId) {
        return this.authorRepository.findById(authorId).orElseThrow(() -> new InvalidAuthorIdException(authorId));
    }

    @Override
    public List<Author> findByIds(List<Long> ids) {
        return this.authorRepository.findAllById(ids);
    }
}
