package com.sparta.individualassignment.dto;

import com.sparta.individualassignment.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequestDto {

    private String username;
//    private User username;
    private String title;
    private String contents;
}
