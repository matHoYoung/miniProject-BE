package com.fortune.fortune.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;
    private String dateOfBirth;

}
