package com.gamingworld.app.gamingworld.profile.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamingworld.app.gamingworld.profile.domain.model.entity.*;
import com.gamingworld.app.gamingworld.profile.domain.persitence.*;
import com.gamingworld.app.gamingworld.profile.domain.service.ProfileService;
import com.gamingworld.app.gamingworld.security.domain.persistence.UserRepository;
import com.gamingworld.app.gamingworld.shared.exception.ResourceNotFoundException;
import com.gamingworld.app.gamingworld.shared.exception.ResourceValidationException;
import com.gamingworld.app.gamingworld.shared.inbound.game.domain.model.entity.Game;
import com.gamingworld.app.gamingworld.shared.inbound.game.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService{
    private final Validator validator;
    private static final String ENTITY = "Profile";
    private final UserRepository userRepository;


    public ProfileServiceImpl(Validator validator, UserRepository userRepository) {
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @Autowired
    private GameService gameService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private GameExperienceRepository gameExperienceRepository;

    @Autowired
    private FavoriteGameRepository favoriteGameRepository;

    @Autowired
    private StreamerSponsorRepository streamerSponsorRepository;

    @Autowired
    private StreamingCategoryRepository streamingCategoryRepository;

    @Autowired
    private TournamentExperienceRepository tournamentExperienceRepository;

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Profile findByUserId(Long userId) {
        return profileRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    /* We automatically create a profile for new users, so we do not need extra profiles for now.
    @Override
    public Profile create(Profile profile) {
        Set<ConstraintViolation<Profile>> violations = validator.validate(profile);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        User user = userRepository.getById(profile.getUser().getId());
        profile.setUser(user);
        return profileRepository.save(profile);
    }
    */

    @Override
    public Profile updateByUserId(Long userId, Profile newProfile) {
        Set<ConstraintViolation<Profile>> violations = validator.validate(newProfile);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("USER", userId);


        Profile currentProfile = profileRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));

        if(newProfile.getId() == null)
            newProfile.setId(currentProfile.getId());

        for (GameExperience gameExperience:
        newProfile.getGameExperiences()) {
            gameExperience.setProfile(currentProfile);

            if (gameExperience.getGameId() != null)
            {
                Optional<Game> game = gameService.findById(gameExperience.getGameId());

                game.ifPresent(value -> gameExperience.setGameName(value.getName()));
            }
        }
        for (FavoriteGame favoriteGame:
                newProfile.getFavoriteGames()) {
            favoriteGame.setProfile(currentProfile);

            if (favoriteGame.getGameId() != null)
            {
                Optional<Game> game = gameService.findById(favoriteGame.getGameId());

                game.ifPresent(value -> favoriteGame.setGameName(value.getName()));
            }
        }
        for (StreamerSponsor streamerSponsor:
                newProfile.getStreamerSponsors()) {
            streamerSponsor.setProfile(currentProfile);
        }
        for (StreamingCategory streamingCategory:
                newProfile.getStreamingCategories()) {
            streamingCategory.setProfile(currentProfile);
        }
        for (TournamentExperience tournamentExperience:
                newProfile.getTournamentExperiences()) {
            tournamentExperience.setProfile(currentProfile);
        }

        // We delete previous elements that are non present in the update request

        List<GameExperience> deletedGameExperiences = currentProfile.getGameExperiences();
        ObjectMapper mapper = new ObjectMapper();
        String jsonUser = "";

        try {
            jsonUser = mapper.writeValueAsString(deletedGameExperiences);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonUser);
        deletedGameExperiences.removeAll(newProfile.getGameExperiences());
        System.out.println(deletedGameExperiences.size());
        List<FavoriteGame> deletedFavoriteGames = currentProfile.getFavoriteGames();
        deletedFavoriteGames.removeAll(newProfile.getFavoriteGames());

        List<StreamerSponsor> deletedStreamerSponsors = currentProfile.getStreamerSponsors();
        deletedStreamerSponsors.removeAll(newProfile.getStreamerSponsors());

        List<StreamingCategory> deletedStreamingCategories = currentProfile.getStreamingCategories();
        deletedStreamingCategories.removeAll(newProfile.getStreamingCategories());

        List<TournamentExperience> deletedTournamentExperiences = currentProfile.getTournamentExperiences();
        deletedTournamentExperiences.removeAll(newProfile.getTournamentExperiences());

        gameExperienceRepository.deleteAllInBatch(deletedGameExperiences);
        favoriteGameRepository.deleteAllInBatch(deletedFavoriteGames);
        streamerSponsorRepository.deleteAllInBatch(deletedStreamerSponsors);
        streamingCategoryRepository.deleteAllInBatch(deletedStreamingCategories);
        tournamentExperienceRepository.deleteAllInBatch(deletedTournamentExperiences);

        newProfile.setUser(userRepository.getById(userId));
        newProfile.setId(currentProfile.getId());

        profileRepository.saveAndFlush(newProfile);

        return profileRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
