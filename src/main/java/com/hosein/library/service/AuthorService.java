package com.hosein.library.service;

import com.hosein.library.entity.Author;
import com.hosein.library.reposiroty.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }


    public Author updateById(Long id, Author updateauthor) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        Author author;
        if (optionalAuthor.isPresent()) {
            author = optionalAuthor.get();
            author.setName(updateauthor.getName());
            author.setEmail(updateauthor.getEmail());
            return authorRepository.save(author);
        } else {
            throw new EntityNotFoundException("Author not found with id " + id);
        }
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
