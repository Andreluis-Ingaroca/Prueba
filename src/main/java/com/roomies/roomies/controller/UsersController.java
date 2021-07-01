package com.roomies.roomies.controller;

import com.roomies.roomies.domain.model.Landlord;
import com.roomies.roomies.domain.model.Leaseholder;
import com.roomies.roomies.domain.model.User;
import com.roomies.roomies.domain.service.LandlordService;
import com.roomies.roomies.domain.service.LeaseholderService;
import com.roomies.roomies.domain.service.UserService;
import com.roomies.roomies.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private LeaseholderService leaseholderService;

    @Autowired
    private LandlordService landlordService;


    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable){
        Page<User> userPage = userService.getAllUsers(pageable);
        List<UserResource> resources = userPage.getContent().stream().map(
                this::convertToResourceUser).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }


    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
        return convertToResourceUser(userService.createUser(convertToEntityUser(resource)));
    }


    @PostMapping("/users/{userId}/plans/{planId}/leaseholders")
    public LeaseholderResource createLeaseholder(
            @PathVariable (name = "userId") Long userId,
            @PathVariable (name = "planId") Long planId,
            @Valid @RequestBody SaveLeaseholderResource resource){
        return convertToResourceLeaseholder(leaseholderService.createLeaseholder(userId,planId,convertToEntityLeaseholder(resource)));
    }


    @PostMapping("/users/{userId}/plans/{planId}/landlords")
    public LandlordResource createLandlord(
            @PathVariable (name = "userId") Long userId,
            @PathVariable (name = "planId") Long planId,
            @Valid @RequestBody SaveLandlordResource resource)
    {
        return convertToResourceLandlord(landlordService.createLandlord(userId,planId,convertToEntityLandlord(resource)));
    }

    private User convertToEntityUser(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResourceUser(User entity){
        return mapper.map(entity, UserResource.class);
    }

    private Leaseholder convertToEntityLeaseholder(SaveLeaseholderResource resource){
        return mapper.map(resource,Leaseholder.class);
    }

    private LeaseholderResource convertToResourceLeaseholder(Leaseholder entity){
        return mapper.map(entity,LeaseholderResource.class);
    }

    private Landlord convertToEntityLandlord(SaveLandlordResource resource) {
        return mapper.map(resource, Landlord.class);
    }

    private LandlordResource convertToResourceLandlord(Landlord entity) {
        return mapper.map(entity, LandlordResource.class);
    }

}
