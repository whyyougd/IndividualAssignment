package com.sparta.individualassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp="^[a-zA-Z0-9]{3,}$", message="아이디를 알파벳과 숫자로만 구성하여 3자 이상으로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp="^[a-zA-Z0-9]{3,}$", message="비밀번호를 알파벳과 숫자로만 구성하여 4자 이상으로 입력해주세요.")
    private String password;

    private String email;

    private boolean admin = false;
    private String adminToken = "";
}
