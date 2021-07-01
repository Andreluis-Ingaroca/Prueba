package com.roomies.roomies;

import com.roomies.roomies.domain.model.Post;
import com.roomies.roomies.domain.model.User;
import com.roomies.roomies.domain.repository.PaymentMethodRepository;
import com.roomies.roomies.domain.repository.UserRepository;
import com.roomies.roomies.domain.service.PaymentMethodService;
import com.roomies.roomies.domain.service.UserService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import com.roomies.roomies.service.PaymentMethodServiceImpl;
import com.roomies.roomies.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }

        @Bean
        public PaymentMethodService paymentMethodService() {
            return new PaymentMethodServiceImpl();
        }
    }


}
