package com.fortune.fortune.service;

import com.fortune.fortune.model.Fortune;
import com.fortune.fortune.model.FortuneEnum;
import com.fortune.fortune.model.User;
import com.fortune.fortune.model.UserFortune;
import com.fortune.fortune.repository.FortuneRepository;
import com.fortune.fortune.repository.UserFortuneRepository;
import com.fortune.fortune.repository.UserRepository;
import com.fortune.fortune.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FortuneService {
    private final FortuneRepository fortuneRepository;
    private final UserFortuneRepository userFortuneRepository;
    private final UserRepository userRepository;

    @Autowired
    public FortuneService(FortuneRepository fortuneRepository, UserFortuneRepository userFortuneRepository, UserRepository userRepository) {
        this.fortuneRepository = fortuneRepository;
        this.userFortuneRepository = userFortuneRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String showFortune(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        //Long qty = fortuneRepository.countByUserNotAndFortuneNotIn();
        Long userId = userDetails.getUser().getId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        if(user.getFortuneEnum() == FortuneEnum.NOT_FORTUNE){
            long qty = fortuneRepository.count();
            int idx = (int) (Math.random() * qty);
            Long longId = Long.valueOf(idx);
            Fortune randomFortune = fortuneRepository.findAllById(longId);

            UserFortune userFortune = new UserFortune(randomFortune.getFortune(), userDetails.getUser().getId());

            userFortuneRepository.save(userFortune);

            user.updateByCheckfortune();

            return userFortune.getFortunecontents();
        }
        UserFortune userFortune1= userFortuneRepository.findByUserid(userId);

        return userFortune1.getFortunecontents();
    }
}

