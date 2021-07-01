package com.roomies.roomies;

import com.roomies.roomies.domain.model.Post;
import com.roomies.roomies.domain.model.Plan;
import com.roomies.roomies.domain.repository.PaymentMethodRepository;
import com.roomies.roomies.domain.repository.PlanRepository;
import com.roomies.roomies.domain.service.PaymentMethodService;
import com.roomies.roomies.domain.service.PlanService;
import com.roomies.roomies.domain.service.UserService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import com.roomies.roomies.service.PaymentMethodServiceImpl;
import com.roomies.roomies.service.PlanServiceImpl;
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
public class PlanServiceImplTest {

    @MockBean
    private PlanRepository planRepository;


    @Autowired
    private PlanService planService;

    @TestConfiguration
    static class PlanServiceImplTestConfiguration{
        @Bean
        public PlanService planService(){
            return new PlanServiceImpl();
        }

    }


    @Test
    @DisplayName("When getPlanByName With Valid Name Then Returns Plan")
    public void whenGetPlanByNameWithValidNameThenReturnsPlan(){

        //Arrange
        String name= "Gratis";
        Plan plan=new Plan().setId(1L).setName(name);

        planRepository.save(plan);

        when(planRepository.findByName(name)).thenReturn(Optional.of(plan));

        //Act
        Plan foundPlan=planService.getPlanByName(name);

        //Assert
        assertThat(foundPlan.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When getPlanByName With Invalid Name Then Returns Resource Not Found Exception")
    public void whenGetPlanByNameWithInvalidNameThenReturnsResourceNotFoundException(){
        //Arrange
        String name= "Te";
        String template="Resource %s not found for %s with value %s";
        when(planRepository.findByName(name)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Plan","Name",name);

        //Act
        Throwable exception= catchThrowable(()->{
            Plan foundPlan=planService.getPlanByName(name);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
