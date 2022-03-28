package com.sparta.individualassignment.repository;

import com.sparta.individualassignment.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByModifiedAtDesc();

//    List<Blog> findAllById();
}
