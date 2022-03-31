package com.sparta.individualassignment.controller;

import com.sparta.individualassignment.dto.BlogRequestDto;
import com.sparta.individualassignment.model.Blog;
import com.sparta.individualassignment.model.Comment;
import com.sparta.individualassignment.repository.BlogRepository;
import com.sparta.individualassignment.repository.CommentRepository;
import com.sparta.individualassignment.security.UserDetailsImpl;
import com.sparta.individualassignment.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;
    private final CommentRepository commentRepository;



    // 작성
    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto){
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }


    //   조회
    @GetMapping("/api/blogs")
    public List<Blog> getBlog(){

        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/blogs/{id}")
    public Blog getOneBlog(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){

        Blog blog = blogRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        List<Comment> comment = commentRepository.findAllByBlogIdOrderByModifiedAtDesc(id);
        if(userDetails == null){
            model.addAttribute("user","null");
        }else{

            model.addAttribute("user",userDetails.getUser().getUsername());
        }
        model.addAttribute("editcomment",new Comment());
        model.addAttribute("postcomment",new Comment());
        model.addAttribute("comment", comment);
        model.addAttribute("blog",blog);
    //        comment.get(0).getUser().getUsername()
        return blog;
    }

    // 수정
    @PutMapping("/api/blogs/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        blogService.update(id,requestDto);
        return id;
    }

    // 삭제
    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id){
        blogRepository.deleteById(id);
        return id;
    }

}

//import com.sparta.individualassignment.dto.BlogRequestDto;
//import com.sparta.individualassignment.model.Blog;
//import com.sparta.individualassignment.model.Comment;
//import com.sparta.individualassignment.repository.BlogRepository;
//import com.sparta.individualassignment.repository.CommentRepository;
//import com.sparta.individualassignment.repository.UserRepository;
//import com.sparta.individualassignment.security.UserDetailsImpl;
//import com.sparta.individualassignment.service.BlogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
////@ResponseBody 이거 추가하면 타임리프 작동을 안한다
//
//public class BlogController {
//
//    @Autowired
//    BlogRepository blogRepository;
//
//    @Autowired
//    BlogService blogService;
//
//    @Autowired
//    CommentRepository commentRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//
//
//    @GetMapping("/")
//    public String getIndex(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        List<Blog> blog = blogRepository.findAllByOrderByModifiedAtDesc();
//        //blog.getTotalElements(); // 전체데이터 건수
//
//        if(userDetails == null){
//            model.addAttribute("username","null");
//        }else{
//
//            model.addAttribute("username",userDetails.getUser().getUsername());
//        }
//
//
//        return "index";
//    }
//
//    //게시글 작성 페이지
//    @GetMapping("/api/blogs")
//    public String getNotice( Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        Blog blog = new Blog();
//        if(userDetails == null){
//            model.addAttribute("user","null");
//        }else{
//
//            model.addAttribute("user",userDetails.getUser().getUsername());
//        }
//        model.addAttribute("blog", blog);
//        return "index";
//    }
//
//    // 게시글 작성
//    @PostMapping("/api/blogs")
//    public String createNotice(@AuthenticationPrincipal UserDetailsImpl userDetails,@ModelAttribute BlogRequestDto requestDto){
//        requestDto.setUsername(userDetails.getUsername());
//        Blog blog = new Blog(requestDto);
//        blogRepository.save(blog);
//        return "redirect:/";
//    }
//
//
//
//    //게시글 한개 조회페이지
//    @GetMapping("/api/blogs/{id}")
//    public String getOneblog(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
//
//        Blog blog = blogRepository.findById(id).orElseThrow(
//                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
//        );
//
//        List<Comment> comment = commentRepository.findAllByBlogIdOrderByModifiedAtDesc(id);
//        if(userDetails == null){
//            model.addAttribute("user","null");
//        }else{
//
//            model.addAttribute("user",userDetails.getUser().getUsername());
//        }
//        model.addAttribute("editcomment",new Comment());
//        model.addAttribute("postcomment",new Comment());
//        model.addAttribute("comment", comment);
//        model.addAttribute("blog",blog);
////        comment.get(0).getUser().getUsername()
//        return "detail";
//    }
//
//
//    @GetMapping("/api/blogs/{id}/edit")
//    public String getEditblog(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        Blog blog = blogRepository.findById(id).orElseThrow(
//                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
//        );
//        if(userDetails == null){
//            model.addAttribute("user","null");
//        }else{
//
//            model.addAttribute("user",userDetails.getUser().getUsername());
//        }
//        model.addAttribute("blog",blog);
//        return "edit";
//    }
//
//
//    @PutMapping("/api/blogs/{id}/edit")
//    public String updateblog(@PathVariable Long id, @ModelAttribute BlogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        requestDto.setUsername(userDetails.getUsername());
//        blogService.update(id, requestDto);
//        return "redirect:/";
//
//    }
//
//    @DeleteMapping("/api/blogs/{id}")
//    public String deleteblog(@PathVariable Long id){
//        blogRepository.deleteById(id);
//        return "redirect:/";
//    }
//
//}
