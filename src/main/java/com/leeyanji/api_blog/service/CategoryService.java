package com.leeyanji.api_blog.service;

import com.leeyanji.api_blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    Page<Category> findAllByNameContaining(String string, Pageable pageable);
    Iterable<Category> findAll();
    Optional<Category> findById(Long id);
    void save(Category category);
    void delete(Long id);
}
