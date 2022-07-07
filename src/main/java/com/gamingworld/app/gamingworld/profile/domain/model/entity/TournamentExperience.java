package com.gamingworld.app.gamingworld.profile.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tournament_experiences")
public class TournamentExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer position;

    @JsonIgnore
    @ManyToOne //(targetEntity = Profile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof TournamentExperience))
            return false;
        TournamentExperience tournamentExperience = (TournamentExperience) obj;

        if ((tournamentExperience.id == null && id != null) || (tournamentExperience.id != null && id == null))
            return false;

        return tournamentExperience.id.equals(id);
    }
}
