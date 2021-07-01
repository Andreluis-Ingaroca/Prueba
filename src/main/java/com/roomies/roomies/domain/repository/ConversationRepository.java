package com.roomies.roomies.domain.repository;

import com.roomies.roomies.domain.model.Conversation;
import com.roomies.roomies.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {
    Page<Conversation> findBySenderId(Long profileId, Pageable pageable);
}
