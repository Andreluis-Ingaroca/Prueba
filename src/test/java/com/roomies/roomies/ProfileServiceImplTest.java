package com.roomies.roomies;

import com.roomies.roomies.domain.model.Profile;
import com.roomies.roomies.domain.repository.PaymentMethodRepository;
import com.roomies.roomies.domain.repository.PlanRepository;
import com.roomies.roomies.domain.repository.ProfileRepository;
import com.roomies.roomies.domain.repository.UserRepository;
import com.roomies.roomies.domain.service.PaymentMethodService;
import com.roomies.roomies.domain.service.PlanService;
import com.roomies.roomies.domain.service.ProfileService;
import com.roomies.roomies.domain.service.UserService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import com.roomies.roomies.service.PaymentMethodServiceImpl;
import com.roomies.roomies.service.PlanServiceImpl;
import com.roomies.roomies.service.ProfileServiceImpl;
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
public class ProfileServiceImplTest {

    @MockBean
    private ProfileRepository profileRepository;

    @MockBean
    private PaymentMethodRepository paymentMethodRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PlanRepository planRepository;

    @Autowired
    private ProfileService profileService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration{
        @Bean
        public ProfileService profileService(){
            return new ProfileServiceImpl();
        }

        @Bean
        public PaymentMethodService paymentMethodService()
            {return new PaymentMethodServiceImpl(); }
        @Bean
        public UserService userService()
        {return new UserServiceImpl(); }
        @Bean
        public PlanService planService()
        {return new PlanServiceImpl(); }
    }


    @Test
    @DisplayName("When getUserByName With Valid Name Then Returns User")
    public void whenGetUserByNameWithValidNameThenReturnsUser(){
        /*
        //Arrange
        String name= "Luis";
        //Profile profile =new Profile().setId(1L).setName(name);

        profileRepository.save(profile);

        when(profileRepository.findByName(name)).thenReturn(Optional.of(profile));

        //Act
        Profile foundProfile = profileService.getProfileByName(name);

        //Assert
        assertThat(foundProfile.getName()).isEqualTo(name);*/
    }

    @Test
    @DisplayName("When getUserByName With Invalid Name Then Returns Resource Not Found Exception")
    public void whenGetUserByNameWithInvalidNameThenReturnsResourceNotFoundException(){
        /*
        String name= "Te";
        String template="Resource %s not found for %s with value %s";
        when(profileRepository.findByName(name)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Profile","Name",name);

        //Act
        Throwable exception= catchThrowable(()->{
            Profile foundProfile = profileService.getProfileByName(name);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);*/
    }

}
