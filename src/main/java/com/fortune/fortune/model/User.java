package com.fortune.fortune.model;

import com.fortune.fortune.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String dateofbirth;

    @Column(nullable = false)
    private  String zodiacsign;

    @Column(nullable = false)
    private  String starposition;

    @Column(nullable = false)
    private boolean checkdiary;

    @Column(nullable = false)
    private FortuneEnum fortuneEnum;




    public User(String username, String password, String nickname, String dateofbirth, String zodiacsign, String starposition) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.dateofbirth = dateofbirth;
        this.zodiacsign = zodiacsign;
        this.starposition = starposition;
        this.fortuneEnum = FortuneEnum.NOT_FORTUNE;
    }
    public void updateByCheckDiary(boolean checkDiary){
        this.checkdiary = checkDiary;
    }
    public void updateByCheckfortune(){
        this.fortuneEnum = FortuneEnum.FORTUNE;
    }
}
