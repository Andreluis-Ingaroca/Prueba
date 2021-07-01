package com.roomies.roomies.controller;

import com.roomies.roomies.domain.model.Leaseholder;
import com.roomies.roomies.domain.service.LeaseholderService;
import com.roomies.roomies.resource.LeaseholderResource;
import com.roomies.roomies.resource.SaveLandlordResource;
import com.roomies.roomies.resource.SaveLeaseholderResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LeaseholdersController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LeaseholderService leaseholderService;


    @GetMapping("/leaseholders")
    public Page<LeaseholderResource> getAllLeaseHolder(Pageable pageable){
        Page<Leaseholder> leaseholderPage = leaseholderService.getAllLeaseholder(pageable);
        List<LeaseholderResource> resources = leaseholderPage.getContent().stream().map(
                this::convertToResourceLeaseholder).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }


    @GetMapping("/leaseholders/{leaseholderId}")
    public LeaseholderResource getLeaseholderById(@PathVariable Long leaseholderId){
        return convertToResourceLeaseholder(leaseholderService.getLeaseholderById(leaseholderId));
    }


    @PutMapping("leaseholders/{leaseholderId}")
    public LeaseholderResource updateLeaseholder(@PathVariable Long leaseholderId,@Valid @RequestBody SaveLeaseholderResource resource){
        return convertToResourceLeaseholder(leaseholderService.updateLeaseholder(leaseholderId,convertToEntityLeaseholder(resource)));
    }


    @DeleteMapping("leaseholders/{leaseholderId}")
    public ResponseEntity<?> deleteLeaseholder(@PathVariable Long leaseholderId){
        return leaseholderService.deleteLeaseholder(leaseholderId);
    }


    @PostMapping("leaseholders/{leaseholderId}/posts/{postId}")
    public LeaseholderResource assignLeaseholderPost(@PathVariable Long leaseholderId,@PathVariable Long postId){
        return convertToResourceLeaseholder(leaseholderService.assignLeaseholderPost(leaseholderId,postId));
    }


    @DeleteMapping("leaseholders/{leaseholderId}/posts/{postId}")
    public LeaseholderResource unassignLeaseholderPost(@PathVariable Long leaseholderId,@PathVariable Long postId){
        return convertToResourceLeaseholder(leaseholderService.unAssignLeaseholderPost(leaseholderId,postId));
    }

    private Leaseholder convertToEntityLeaseholder(SaveLeaseholderResource resource){
        return mapper.map(resource,Leaseholder.class);
    }

    private LeaseholderResource convertToResourceLeaseholder(Leaseholder entity){
        return mapper.map(entity,LeaseholderResource.class);
    }
}
