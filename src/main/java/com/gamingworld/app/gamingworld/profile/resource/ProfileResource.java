package com.gamingworld.app.gamingworld.profile.resource;


import com.gamingworld.app.gamingworld.profile.domain.model.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileResource {
    private Long id;
    //private User user;
    private List<GameExperience> gameExperiences;
    private List<FavoriteGame> favoriteGames;
    private List<StreamerSponsor> streamerSponsors ;
    private List<TournamentExperience> tournamentExperiences ;
    private List<StreamingCategory> streamingCategories;
}
