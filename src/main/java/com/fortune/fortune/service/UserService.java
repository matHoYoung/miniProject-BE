package com.fortune.fortune.service;

import com.fortune.fortune.dto.SignupRequestDto;
import com.fortune.fortune.model.User;
import com.fortune.fortune.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        String nickname = requestDto.getNickname();
        Optional<User> found2 = userRepository.findByNickname(nickname);
        if (found2.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        String dateOfBirth = requestDto.getDateOfBirth();
        int[] date = Arrays.stream(dateOfBirth.split("\\."))
                .mapToInt(Integer::parseInt)
                .toArray();



        String zodiacSign = getZodiacSign(date[0]);
        String starPosition = getstarPosition(date[1],date[2]);

        User user = new User(username, password, nickname, dateOfBirth, zodiacSign, starPosition );
        userRepository.save(user);
    }

    private String getstarPosition(int Month, int day) {
        String starPosition = "";
        int starday = Month*100 +day;
        if (starday >= 120  && starday <= 218) {
            starPosition = "AQUARIUS";

        } else if (starday >= 219  && starday <= 320 ) {
            starPosition = "PISCES";
            
        } else if (starday >= 321  && starday <= 419) {
            starPosition = "ARIES";

        } else if (starday >= 420  && starday <= 520 ) {
            starPosition = "TAURUS";

        } else if (starday >= 521  && starday <= 621 ) {
            starPosition = "GEMINI";

        } else if (starday >= 622  && starday <= 722 ) {
            starPosition = "CANOER";

        } else if (starday >= 723  && starday <= 822 ) {
            starPosition = "LEO";

        } else if (starday >= 823  && starday <= 923) {
            starPosition = "VIRGO";

        } else if (starday >= 924 && starday <= 1022 ) {
            starPosition = "LIBRA";

        } else if (starday >= 1023 && starday <= 1122 ) {
            starPosition = "SCORPIUS";

        } else if (starday >= 1123 && starday <= 1224 ) {
            starPosition = "SAGITTARIUS";

        } else{
            starPosition = "CAPRICORNUS";
        }
        return starPosition;
    }

    private String getZodiacSign(int year) {
        String zodiacSign = "";

        switch(year%12) {
            case 0:
                zodiacSign = "MONKEY";
                break;
            case 1:
                zodiacSign = "CHICKEN";
                break;
            case 2:
                zodiacSign = "DOG";
                break;
            case 3:
                zodiacSign = "PIG";
                break;
            case 4:
                zodiacSign = "RAT";
                break;
            case 5:
                zodiacSign = "COW";
                break;
            case 6:
                zodiacSign = "TIGER";
                break;
            case 7:
                zodiacSign = "RABBIT";
                break;
            case 8:
                zodiacSign = "DRAGON";
                break;
            case 9:
                zodiacSign = "SNAKE";
                break;
            case 10:
                zodiacSign = "HORSE";
                break;
            case 11:
                zodiacSign = "SHEEP";
                break;
        }
        return zodiacSign;


    }
}