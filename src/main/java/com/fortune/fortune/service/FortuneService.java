package com.fortune.fortune.service;

import com.fortune.fortune.model.Fortune;
import com.fortune.fortune.model.User;
import com.fortune.fortune.repository.FortuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FortuneService {
    private final FortuneRepository fortuneRepository;

    @Autowired
    public FortuneService(FortuneRepository fortuneRepository) {
        this.fortuneRepository = fortuneRepository;
    }

    public Fortune showFortune() {
        List<Fortune> fortuneList = new ArrayList<>();
        User user = new User();
        Long qty = fortuneRepository.countByUserNotAndFortuneNotIn(user, fortuneList);
        //가져온 개수 중 랜덤한 하나의 인덱스를 뽑는다.
        int idx = (int) (Math.random() * qty);

        //페이징하여 하나만 추출해낸다.
        Page<Fortune> fortunePage = fortuneRepository.findAllByUserNotAndFortuneNotIn(
                user,
                fortuneList,
                PageRequest.of(idx, 1)
        );
        String x="운세";
        Fortune getFortune = new Fortune(user, x);
        if (fortunePage.hasContent()) {
            getFortune = fortunePage.getContent().get(0);
        }

        return getFortune;
    }
}


