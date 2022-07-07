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
@RequiredArgsConstructor
@Table(name = "participant")
@Inheritance(strategy = InheritanceType.JOINED)


public class Participant extends AuditModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull private Integer points;

    @OneToOne
    @NonNull private Profile participantProfile;

    private Long tournamentId;



}
