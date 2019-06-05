package com.leeyanji.api_blog.repository;

import com.leeyanji.api_blog.model.Blog;
import com.leeyanji.api_blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Page<Blog> findAllByNameContaining(String string, Pageable pageable);
    int getCountByCategory(Category category);
}
