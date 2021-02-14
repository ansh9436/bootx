package com.ansh.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BootxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootxApplication.class, args);
    }

}
