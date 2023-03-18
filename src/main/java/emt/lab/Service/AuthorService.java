package emt.lab.Service;

import emt.lab.Model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long authorId);
    List<Author> findByIds(List<Long> ids);
}
