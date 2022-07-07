package com.gamingworld.app.gamingworld.tournament.domain.model.entity;

import com.gamingworld.app.gamingworld.profile.domain.model.entity.Profile;
import com.gamingworld.app.gamingworld.shared.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team_participant")
@Inheritance(strategy = InheritanceType.JOINED)
public class TeamParticipant extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Profile participantProfile;

    private Long tournamentId;

    private Long teamId;

}