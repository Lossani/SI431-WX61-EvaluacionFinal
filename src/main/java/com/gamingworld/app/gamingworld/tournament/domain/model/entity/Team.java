package com.gamingworld.app.gamingworld.tournament.domain.model.entity;

import com.gamingworld.app.gamingworld.profile.domain.model.entity.Profile;
import com.gamingworld.app.gamingworld.shared.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team")
@Inheritance(strategy = InheritanceType.JOINED)
public class Team extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull private String name;
    
    @Column
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(targetEntity = Profile.class)
    private Profile ownerProfile;

    @OneToMany
    private List<Profile> members;

    @Column
    private Integer points = 0;

    private Long tournamentId;
}
