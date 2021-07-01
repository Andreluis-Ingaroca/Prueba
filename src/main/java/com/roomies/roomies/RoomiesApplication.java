package com.roomies.roomies;

import com.roomies.roomies.domain.service.UserService;
import com.roomies.roomies.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RoomiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomiesApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
