package com.gamingworld.app.gamingworld.profile.mapping;

import com.gamingworld.app.gamingworld.profile.domain.model.entity.Profile;
import com.gamingworld.app.gamingworld.profile.resource.ProfileResource;
import com.gamingworld.app.gamingworld.profile.resource.SaveProfileResource;
import com.gamingworld.app.gamingworld.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ProfileMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ProfileResource toResource(Profile model) { return mapper.map(model, ProfileResource.class);
    }

    public Page<ProfileResource> modelListToPage(List<Profile> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ProfileResource.class), pageable, modelList.size());
    }

    public Profile toModel(SaveProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }
}

