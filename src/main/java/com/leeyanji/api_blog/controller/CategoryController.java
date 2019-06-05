package com.leeyanji.api_blog.controller;

import com.leeyanji.api_blog.model.Blog;
import com.leeyanji.api_blog.model.Category;
import com.leeyanji.api_blog.service.BlogService;
import com.leeyanji.api_blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogService blogService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<Category>> getAllCategory(Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        return new ResponseEntity<Page<Category>>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Blog>> getCategoryById(@PathVariable long id, Pageable pageable) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            Page<Blog> blogs = blogService.findAllByCategory(category.get(), pageable);
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createCategory(@RequestBody Category category, UriComponentsBuilder builder) {
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category) {
        Optional<Category> currentCategory = categoryService.findById(id);
        if (currentCategory.isPresent()) {
            currentCategory.get().setName(category.getName());
            categoryService.save(currentCategory.get());
            return new ResponseEntity<>(currentCategory.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> deleteCategory(@PathVariable long id) {
        Optional<Category> currentCategory = categoryService.findById(id);
        if (currentCategory.isPresent()) {
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
