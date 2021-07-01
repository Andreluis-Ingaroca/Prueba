package com.roomies.roomies;

import com.roomies.roomies.domain.repository.*;
import com.roomies.roomies.domain.repository.ReviewRepository;
import com.roomies.roomies.domain.service.*;
import com.roomies.roomies.service.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
public class ReviewServiceImplTest {
    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private ProfileRepository profileRepository;


    @TestConfiguration
    static class ReviewServiceImplTestConfiguration {
        @Bean
        public ReviewService reviewService(){
            return new ReviewServiceImpl();
        }

        @Bean
        public PostService postService(){return new PostServiceImpl();}

        @Bean
        public ProfileService userService(){return new ProfileServiceImpl();}
    }



}
