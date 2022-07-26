package com.fortune.fortune.service;

import com.fortune.fortune.model.Diary;
import com.fortune.fortune.model.Fortune;
import com.fortune.fortune.repository.FortuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FortuneService {
    private final FortuneRepository fortuneRepository;

    @Autowired
    public FortuneService(FortuneRepository fortuneRepository) {
        this.fortuneRepository = fortuneRepository;
    }

    public Fortune showFortune() {
        //Long qty = fortuneRepository.countByUserNotAndFortuneNotIn();

        long qty = fortuneRepository.count();
        int idx = (int) (Math.random() * qty);
        Long longId = Long.valueOf(idx);
        Fortune randomFortune = fortuneRepository.findAllById(longId);
        Diary diary = new Diary();

        return randomFortune;
    }
}
