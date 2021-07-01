package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.User;
import com.roomies.roomies.domain.repository.ProfileRepository;
import com.roomies.roomies.domain.repository.UserRepository;
import com.roomies.roomies.domain.service.UserService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
    }

    @Override
    public User createUser(User user) {
        Pageable pageable = PageRequest.of(0,10000);
        Page<User> userPage = userRepository.findAll(pageable);

        if(userPage!=null)
            userPage.forEach(user1 -> {
                if(user1.getEmail().equals(user.getEmail()))
                    throw new ResourceNotFoundException("There is already another user with the same email");
            });

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User userr = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userr.setEmail(userRequest.getEmail())
                .setPassword(userRequest.getPassword());
        return userRepository.save(userr);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User","Email",email));
        String defaultPassword = passwordEncoder.encode(user.getPassword());
        if(user.getEmail().equals(email)){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),defaultPassword,DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException("User not found with email "+email);

    }
}
