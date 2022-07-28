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

    // 오늘의 랜덤운세 보여주기
    @Transactional
    public String showFortune(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        //Long qty = fortuneRepository.countByUserNotAndFortuneNotIn();
        Long userId = userDetails.getUser().getId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        if(user.getFortuneEnum() == FortuneEnum.NOT_FORTUNE){
            long qty = fortuneRepository.count();  // DB에 저장된 운세 개수를 가져와서
            int idx = (int) (Math.random() * qty);  // 랜덤한 숫자 추출
            Long longId = Long.valueOf(idx); // Int를 Long으로 타입변경
            Fortune randomFortune = fortuneRepository.findAllById(longId); // 랜덤한 운세를 Repository에서 가져옴.

            UserFortune userFortune = new UserFortune(randomFortune.getFortune(), userDetails.getUser().getId()); // 유저 정보에

            userFortuneRepository.save(userFortune); //오늘의 운세 저장

            user.updateByCheckfortune(); // 운세를 확인하면 FORTUNE 값으로 변경해서 하루에  한 번만 운세확인 가능.

            return userFortune.getFortunecontents(); // 오늘의 운세 return.
        }
        UserFortune userFortune1= userFortuneRepository.findByUserid(userId); // userId로 오늘의 운세를 userFortune에 저장.

        return userFortune1.getFortunecontents();
    }
}

