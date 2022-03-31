package com.sparta.individualassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.individualassignment.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBlogIdOrderByModifiedAtDesc(Long id);
}
