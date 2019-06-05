package com.leeyanji.api_blog.service;

import com.leeyanji.api_blog.model.Blog;
import com.leeyanji.api_blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Page<Blog> findAllByNameContaining(String string, Pageable pageable);
    Optional<Blog> findById(Long id);
    void save(Blog blog);
    void delete(Long id);
}
