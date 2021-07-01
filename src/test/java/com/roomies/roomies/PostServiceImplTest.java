package com.roomies.roomies;

import com.roomies.roomies.domain.model.Post;
import com.roomies.roomies.domain.repository.LandlordRepository;
import com.roomies.roomies.domain.repository.LeaseholderRepository;
import com.roomies.roomies.domain.repository.PostRepository;
import com.roomies.roomies.domain.repository.ReviewRepository;
import com.roomies.roomies.domain.service.LandlordService;
import com.roomies.roomies.domain.service.LeaseholderService;
import com.roomies.roomies.domain.service.PostService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import com.roomies.roomies.service.LandlordServiceImpl;
import com.roomies.roomies.service.LeaseholderServiceImpl;
import com.roomies.roomies.service.PostServiceImpl;
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
public class PostServiceImplTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private LandlordRepository landlordRepository;

    @MockBean
    private LeaseholderRepository leaseholderRepository;

    @Autowired
    private PostService postService;

    @TestConfiguration
    static class PostServiceImplTestConfiguration {
        @Bean
        public PostService postService(){
            return new PostServiceImpl();
        }
    }

    @Test
    @DisplayName("When getPostByTitle With Valid Title Then Returns Post")
    public void whenGetPostByTitleWithValidTitleThenReturnsTitle(){

        //Arrange
        String title= "Casa en la Molina";
        Post post=new Post().setId(1L).setTitle(title);

        //postRepository.save(title);

        when(postRepository.findByTitle(title)).thenReturn(Optional.of(post));

        //Act
        Post foundPost=postService.getPostByTitle(title);

        //Assert
        assertThat(foundPost.getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("When getPostByTitle With Invalid Title Then Returns Resource Not Found Exception")
    public void whenGetPostByTitleWithInvalidTitleThenReturnsResourceNotFoundException(){
        //Arrange
        String title= "String";
        String template="Resource %s not found for %s with value %s";
        when(postRepository.findByTitle(title)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Post","Title",title);

        //Act
        Throwable exception= catchThrowable(()->{
            Post foundPost=postService.getPostByTitle(title);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

}
