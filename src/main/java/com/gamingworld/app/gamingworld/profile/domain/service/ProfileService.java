package com.gamingworld.app.gamingworld.profile.domain.service;

import com.gamingworld.app.gamingworld.profile.domain.model.entity.Profile;

import java.util.List;

public interface ProfileService {
    public List<Profile> getAll();
    public Profile findById(Long id);
    public Profile findByUserId(Long userId);
    //public Profile create(Profile profile);
    public Profile updateByUserId(Long userId, Profile newProfile);
    //public GameExperience addGameExperience(GameExperience gameExperience, Long id);
}
