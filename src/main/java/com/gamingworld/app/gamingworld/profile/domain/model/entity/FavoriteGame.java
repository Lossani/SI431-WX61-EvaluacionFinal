package com.gamingworld.app.gamingworld.profile.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "favorite_games")
@NoArgsConstructor

public class FavoriteGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne(targetEntity = Game.class, fetch = FetchType.LAZY)
    //@JoinColumn(name = "game_id")
    private Long gameId;

    private String gameName;

    @JsonIgnore
    @ManyToOne //(targetEntity = Profile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    //private Long profileId;

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof FavoriteGame))
            return false;
        FavoriteGame favoriteGame = (FavoriteGame) obj;

        if ((favoriteGame.id == null && id != null) || (favoriteGame.id != null && id == null))
            return false;

        return favoriteGame.id.equals(id);
    }
}

