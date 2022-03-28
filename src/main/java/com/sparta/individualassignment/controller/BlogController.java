package com.sparta.individualassignment.controller;

import com.sparta.individualassignment.model.Blog;
import com.sparta.individualassignment.repository.BlogRepository;
import com.sparta.individualassignment.dto.BlogRequestDto;
import com.sparta.individualassignment.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    // 작성
    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto){
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    // 조회
    @GetMapping("/api/blogs")
    public List<Blog> getMemo(){
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    // 특정 글 조회
    @GetMapping("/api/blogs/{id}")
    public Blog getBlog(@PathVariable Long id){
        Blog blogs = blogRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("blogID가 존재하지 않습니다.")
        );
        return blogs;
    }

    // 삭제
    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id){
        blogRepository.deleteById(id);
        return id;
    }

    // 수정
    @PutMapping("/api/blogs/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        blogService.update(id,requestDto);
        return id;
    }

}