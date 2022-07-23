package com.fortune.fortune.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Fortune {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LOGIN_ID", nullable = false) // 유저 ID를 FK로 사용
    private User user;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false일때 중복 허용)
//    @Column(nullable = false)
//    private int fortuneChoice; // 2개 중에 하나 선택!

    @Column(nullable = false)
    private String fortune;

    public Fortune(User userId, String fortune){
        this.fortune = fortune;
        this.user = userId;
    }
}
