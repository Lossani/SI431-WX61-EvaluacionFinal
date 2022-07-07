package com.gamingworld.app.gamingworld.profile.api;

import com.gamingworld.app.gamingworld.profile.domain.model.entity.Profile;
import com.gamingworld.app.gamingworld.profile.domain.service.ProfileService;
import com.gamingworld.app.gamingworld.profile.mapping.ProfileMapper;
import com.gamingworld.app.gamingworld.profile.resource.ProfileResource;
import com.gamingworld.app.gamingworld.profile.resource.SaveProfileResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/profiles")
@Tag(name = "Profile Controller", description = "Basic operation for User profiles")
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileMapper mapper;

    @GetMapping(path = "")
    @Operation(summary = "Retrieves all existing profiles.", description = "Retrieves all existing profiles.")
    public List<Profile> getAll(){
        return profileService.getAll();
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Retrieves a profile by its ID.", description = "Retrieves a profile by its ID.")
    public Profile getById(@PathVariable("id") Long profileId){
        return profileService.findById(profileId);
    }

    @GetMapping(path = "/user/{userId}")
    @Operation(summary = "Retrieves a profile by the owner user ID.", description = "Retrieves a profile by the owner user ID.")
    public Profile getByUserId(@PathVariable("userId") Long userId){
        return profileService.findByUserId(userId);
    }

    /* We automatically create a profile for new users, so we do not need extra profiles for now.
    @PostMapping(path = "")
    public ProfileResource create(@RequestBody SaveProfileResource profile){
        return mapper.toResource(profileService.create(mapper.toModel(profile)));
    }
    */

    @PutMapping(path = "/{userId}")
    @Operation(summary = "Updates a profile by user ID.", description = "Updates a profile by user ID.")
    public ProfileResource updateByUserId(@PathVariable("userId") Long userId, @RequestBody SaveProfileResource profile){
        return mapper.toResource(profileService.updateByUserId(userId, mapper.toModel(profile)));
    }
}
