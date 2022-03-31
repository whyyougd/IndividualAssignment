package com.sparta.individualassignment.service;

import com.sparta.individualassignment.model.Blog;
import com.sparta.individualassignment.model.Comment;
import com.sparta.individualassignment.repository.BlogRepository;
import com.sparta.individualassignment.dto.BlogRequestDto;
import com.sparta.individualassignment.repository.CommentRepository;
import com.sparta.individualassignment.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {


    private final BlogRepository blogRepository;

    @Transactional
    public Long update(Long id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디 존재 X")
        );
//        String myContents = requestDto.getContents();
//        blog.setContents(myContents);
//        blogRepository.save(blog);
//        return blog;
        blog.update(requestDto);
        return blog.getId();
    }


}
