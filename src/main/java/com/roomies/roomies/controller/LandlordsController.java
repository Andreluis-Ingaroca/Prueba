package com.roomies.roomies.controller;

import com.roomies.roomies.domain.model.Landlord;
import com.roomies.roomies.domain.model.Post;
import com.roomies.roomies.domain.service.LandlordService;
import com.roomies.roomies.domain.service.PostService;
import com.roomies.roomies.resource.LandlordResource;
import com.roomies.roomies.resource.PostResource;
import com.roomies.roomies.resource.SaveLandlordResource;
import com.roomies.roomies.resource.SavePostResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LandlordsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LandlordService landlordService;

    @Autowired
    private PostService postService;


    @GetMapping("/landlords")
    public Page<LandlordResource> getAllLandlords(Pageable pageable){
        Page<Landlord> landlordPage =landlordService.getAllLandlords(pageable);
        List<LandlordResource> resources = landlordPage.getContent().stream().map(
                this::convertToResourceLandlord).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }


    @GetMapping("/landlords/{landlordId}")
    public LandlordResource getLandlordById(@PathVariable Long landlordId){
        return convertToResourceLandlord(landlordService.getLandlordById(landlordId));
    }



    @PutMapping("/landlords/{landlordId}")
    public LandlordResource updateLandlord(@PathVariable Long landlordId,@Valid @RequestBody SaveLandlordResource resource){
        return convertToResourceLandlord(landlordService.updateLandlord(landlordId,convertToEntityLandlord(resource)));
    }


    @DeleteMapping("/landlords/{landlordId}")
    public ResponseEntity<?> deleteLandlord(@PathVariable Long landlordId) {
        return landlordService.deleteLandlord(landlordId);
    }


    @PostMapping("/landlord/{landlordId}/posts")
    public PostResource createPost(@PathVariable Long landlordId, @Valid @RequestBody SavePostResource resource){
        return convertToResourcePost(postService.createPost(landlordId,convertToEntityPost(resource)));
    }

    private Landlord convertToEntityLandlord(SaveLandlordResource resource) {
        return mapper.map(resource, Landlord.class);
    }

    private LandlordResource convertToResourceLandlord(Landlord entity) {
        return mapper.map(entity, LandlordResource.class);
    }
    private Post convertToEntityPost(SavePostResource resource){return mapper.map(resource,Post.class);}
    private PostResource convertToResourcePost(Post entity){return mapper.map(entity,PostResource.class);}
}
