package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.Leaseholder;
import com.roomies.roomies.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface LeaseholderService {
    Page<Leaseholder> getAllLeaseholder(Pageable pageable);
    Leaseholder getLeaseholderById(Long leaseholderId);
    Leaseholder createLeaseholder(Long userId,Long planId,Leaseholder leaseholder);
    Leaseholder updateLeaseholder(Long leaseholderId,Leaseholder leaseholderRequest);
    ResponseEntity<?> deleteLeaseholder(Long leaseholderId);
    Leaseholder assignLeaseholderPost(Long leaseholderId,Long postId);
    Leaseholder unAssignLeaseholderPost(Long leaseholderId,Long postId);
    Page<Post> getAllPostsByLeaseholderId(Long leaseholderId, Pageable pageable);
    Leaseholder getLeaseholderByName(String name);
}
