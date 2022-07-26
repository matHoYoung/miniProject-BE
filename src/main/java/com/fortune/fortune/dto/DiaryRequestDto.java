package com.fortune.fortune.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class DiaryRequestDto {
    private String fortune;
    private String contents;
}