package com.roomies.roomies.domain.repository;

import com.roomies.roomies.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
