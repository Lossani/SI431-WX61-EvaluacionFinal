package com.gamingworld.app.gamingworld.profile.domain.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "streamer_sponsors")
public class StreamerSponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne //(targetEntity = Profile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof StreamerSponsor))
            return false;
        StreamerSponsor streamerSponsor = (StreamerSponsor) obj;

        if ((streamerSponsor.id == null && id != null) || (streamerSponsor.id != null && id == null))
            return false;

        return streamerSponsor.id.equals(id);
    }
}
