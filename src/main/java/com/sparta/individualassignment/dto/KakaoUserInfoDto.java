package com.sparta.individualassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;

//    public KakaoUserInfoDto(Long id, String nickname, String email){
//        this.id = id;
//        this.nickname = nickname;
//        this.email = email;
//    }
}
