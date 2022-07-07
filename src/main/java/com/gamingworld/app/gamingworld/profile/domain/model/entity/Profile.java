package com.gamingworld.app.gamingworld.profile.domain.model.entity;

import com.gamingworld.app.gamingworld.security.domain.model.entity.User;
import com.gamingworld.app.gamingworld.shared.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@With
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile extends AuditModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private
    User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<GameExperience> gameExperiences;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<FavoriteGame> favoriteGames;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<StreamerSponsor> streamerSponsors;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<TournamentExperience> tournamentExperiences;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<StreamingCategory> streamingCategories;

    public void addGameExperience(GameExperience entity){
        gameExperiences.add(entity);
    }
    public void addFavouriteGames(FavoriteGame entity) { favoriteGames.add(entity); }
    public void addStreamerSponsor(StreamerSponsor entity) { streamerSponsors.add(entity); }
    public void addTournamentExperience(TournamentExperience entity) { tournamentExperiences.add(entity); }
}
