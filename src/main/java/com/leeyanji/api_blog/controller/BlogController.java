package com.leeyanji.api_blog.controller;

import com.leeyanji.api_blog.model.Blog;
import com.leeyanji.api_blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<Blog>> getAllBlog(Pageable pageable){
        Page<Blog> blogs = blogService.findAll(pageable);
        return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> getBlogById(@PathVariable long id){
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()){
            return new ResponseEntity<Blog>(blog.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBlog(@RequestBody Blog blog, UriComponentsBuilder builder){
        blogService.save(blog);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/blogs/{id}").buildAndExpand(blog.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> updateBlog(@PathVariable long id, @RequestBody Blog blog){
        Optional<Blog> currentBlog = blogService.findById(id);
        if (currentBlog.isPresent()){
            currentBlog.get().setName(blog.getName());
            currentBlog.get().setContent(blog.getContent());
            currentBlog.get().setCategory(blog.getCategory());
            blogService.save(currentBlog.get());
            return new ResponseEntity<>(currentBlog.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> deleteBlog(@PathVariable long id){
        Optional<Blog> currentBlog = blogService.findById(id);
        if (currentBlog.isPresent()){
            blogService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
