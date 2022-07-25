package com.fortune.fortune.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private String zodiacsign;
    private String starposition;
    private String nickname;
}
