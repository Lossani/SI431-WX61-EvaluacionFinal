package com.gamingworld.app.gamingworld.profile.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "game_experiences")
@NoArgsConstructor
public class GameExperience {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne(targetEntity = Game.class, fetch = FetchType.LAZY)
    //@JoinColumn(name = "game_id")
    private Long gameId;

    private String gameName;

    @Column(nullable = false)
    private Integer experience;

    @JsonIgnore
    @ManyToOne //(targetEntity = Profile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof GameExperience))
            return false;
        GameExperience gameExperience = (GameExperience) obj;

        if ((gameExperience.id == null && id != null) || (gameExperience.id != null && id == null))
            return false;

        return gameExperience.id.equals(id);
    }
}
