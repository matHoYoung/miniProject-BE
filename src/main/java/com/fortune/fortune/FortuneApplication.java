package com.fortune.fortune;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableScheduling
@SpringBootApplication
@EnableJpaAuditing //생성일자 수정일자 반영
public class FortuneApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortuneApplication.class, args);

    }


}

