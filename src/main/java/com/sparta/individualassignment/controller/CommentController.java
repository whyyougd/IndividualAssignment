package com.sparta.individualassignment.controller;

import com.sparta.individualassignment.dto.CommentRequestDto;
import com.sparta.individualassignment.model.Blog;
import com.sparta.individualassignment.model.Comment;
import com.sparta.individualassignment.repository.BlogRepository;
import com.sparta.individualassignment.repository.CommentRepository;
import com.sparta.individualassignment.security.UserDetailsImpl;
import com.sparta.individualassignment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BlogRepository blogRepository;

    @PostMapping("/api/blogs/{id}/comment")
    public String createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @ModelAttribute CommentRequestDto requestDto){
        Comment comment = new Comment(requestDto);
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        comment.setUsername(userDetails.getUser());
        comment.setBlog(blog);
        commentRepository.save(comment);
//        return "redirect:/api/blog/{id}";
        return "redirect:/";
    }

    @PutMapping("/api/blogs/{id}/comment/{commentId}")
    public String editComment(@PathVariable Long commentId, @ModelAttribute CommentRequestDto requestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        comment.setComment(requestDto.getComment());

        commentRepository.save(comment);
        return "redirect:/api/blogs/{id}";
    }

    @DeleteMapping("/api/blogs/{id}/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentRepository.deleteById(commentId);
        return "redirect:/api/blog/{id}";
    }

//    @GetMapping("/api/blogs/")

//    @GetMapping("/api/blogs/comment")
//    public String getComment(@AuthenticationPrincipal UserDetailsImpl userDetails)
}

