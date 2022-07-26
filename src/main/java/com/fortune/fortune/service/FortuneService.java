package com.fortune.fortune.service;

import com.fortune.fortune.model.Diary;
import com.fortune.fortune.model.Fortune;
import com.fortune.fortune.model.User;
import com.fortune.fortune.model.UserFortune;
import com.fortune.fortune.repository.FortuneRepository;
import com.fortune.fortune.repository.UserFortuneRepository;
import com.fortune.fortune.repository.UserRepository;
import com.fortune.fortune.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        long qty = fortuneRepository.count();
        int idx = (int) (Math.random() * qty);
        Long longId = Long.valueOf(idx);
        Fortune randomFortune = fortuneRepository.findAllById(longId);

        UserFortune userFortune = new UserFortune(randomFortune.getFortune(), userDetails.getUser().getId());

        if(!userDetails.getUser().isCheckfortune()){

            userFortuneRepository.save(userFortune);

            Long userId = userDetails.getUser().getId();

            Optional<User> getUser = userRepository.findById(userId);
            User user = getUser.get();
            user.setCheckfortune(true);
            userRepository.save(user);
        }

        return userFortune.getFortunecontents();
    }
}
