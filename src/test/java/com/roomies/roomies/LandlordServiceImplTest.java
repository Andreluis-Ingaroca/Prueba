package com.roomies.roomies;

import com.roomies.roomies.domain.model.Landlord;
import com.roomies.roomies.domain.model.Leaseholder;
import com.roomies.roomies.domain.repository.*;
import com.roomies.roomies.domain.repository.LandlordRepository;
import com.roomies.roomies.domain.service.LandlordService;
import com.roomies.roomies.domain.service.LandlordService;
import com.roomies.roomies.domain.service.LeaseholderService;
import com.roomies.roomies.domain.service.PostService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import com.roomies.roomies.service.*;
import com.roomies.roomies.service.LandlordServiceImpl;
import com.roomies.roomies.service.LandlordServiceImpl;
import org.assertj.core.api.AssertionsForClassTypes;
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
public class LandlordServiceImplTest {

    @MockBean
    private LandlordRepository landlordRepository;

    @MockBean
    private PlanRepository planRepository;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ConversationRepository conversationRepository;

    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private LandlordService landlordService;

    @TestConfiguration
    static class LandlordServiceImplTestConfiguration {
        @Bean
        public LandlordService landlordService(){
            return new LandlordServiceImpl();
        }
    }

    @Test
    @DisplayName("When getLandlordByName With Valid Name Then Returns Landlord")
    public void whenGetLandlordByNameWithValidNameThenReturnsName(){

        //Arrange
        String name= "String";
        Landlord landlord= (Landlord) new Landlord().setId(1L).setName(name);

        when(landlordRepository.findByName(name)).thenReturn(Optional.of(landlord));

        //Act
        Landlord foundLandlord= (Landlord) landlordService.getLandlordByName(name);

        //Assert
        assertThat(foundLandlord.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When getLandlordByName With Invalid Name Then Returns Resource Not Found Exception")
    public void whenGetLandlordByNameWithInvalidNameThenReturnsResourceNotFoundException(){
        //Arrange
        String name= "String";
        String template="Resource %s not found for %s with value %s";
        when(landlordRepository.findByName(name)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Landlord","Name",name);

        //Act
        Throwable exception= catchThrowable(()->{
            Landlord foundLandlord=landlordService.getLandlordByName(name);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

}
