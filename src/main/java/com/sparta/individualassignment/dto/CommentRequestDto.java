package com.sparta.individualassignment.dto;

import com.sparta.individualassignment.model.Blog;
import com.sparta.individualassignment.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {

    private String comment;
//    private User username;
    private User username;
    private Blog blog;

}
