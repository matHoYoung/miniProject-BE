package com.fortune.fortune.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserFortune {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String fortunecontents;

    @Column(nullable = false)
    private Long userid;

    public UserFortune(String fortune, Long id) {
        this.fortunecontents = fortune;
        this.userid = id;
    }
}
