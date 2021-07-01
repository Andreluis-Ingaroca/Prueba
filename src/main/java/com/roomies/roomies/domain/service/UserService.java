package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Page<User> getAllUsers(Pageable pageable);
    User getUserById(Long userId);
    User createUser(User userr);
    User updateUser(Long userId, User userrRequest);
    ResponseEntity<?> deleteUser(Long userId);
}
