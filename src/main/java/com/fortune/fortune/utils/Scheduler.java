package com.fortune.fortune.utils;

import com.fortune.fortune.model.FortuneEnum;
import com.fortune.fortune.model.User;
import com.fortune.fortune.repository.UserRepository;
import com.fortune.fortune.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성합니다.
@Component // 스프링이 필요 시 자동으로 생성하는 클래스 목록에 추가합니다.
public class Scheduler {

    private final UserRepository userRepository;
    private final UserService userService;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 5 * * *")
    public void updateCheck() throws InterruptedException {  // // 일기작성, 운세확인 유무 초기화
        // 저장된 모든 유저을 조회합니다.
        List<User> userList = userRepository.findAll();
        for (int i=0; i<userList.size(); i++) {
            // 1초에 한 상품 씩 조회합니다 (Naver 제한)
            //TimeUnit.SECONDS.sleep(1); // 사용 안해도 됨
            // i 번째 유저을 꺼냅니다.
            User u = userList.get(i);
            // i 번째 유저 일기 저장 boolean값을 업데이트합니다.
            Long id = u.getId();
            userService.updateBySearch(id, false);
        }
    }
}