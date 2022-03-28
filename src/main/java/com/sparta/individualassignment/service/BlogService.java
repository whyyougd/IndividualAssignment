package com.sparta.individualassignment.service;

import com.sparta.individualassignment.model.Blog;
import com.sparta.individualassignment.repository.BlogRepository;
import com.sparta.individualassignment.dto.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public Long update(Long id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디 존재 X")
        );
        blog.update(requestDto);
        return blog.getId();
    }
}
