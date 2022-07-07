package com.gamingworld.app.gamingworld.profile.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "streaming_categories")
public class StreamingCategory {
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
        if (!(obj instanceof StreamingCategory))
            return false;
        StreamingCategory streamingCategory = (StreamingCategory) obj;

        if ((streamingCategory.id == null && id != null) || (streamingCategory.id != null && id == null))
            return false;

        return streamingCategory.id.equals(id);
    }
}
