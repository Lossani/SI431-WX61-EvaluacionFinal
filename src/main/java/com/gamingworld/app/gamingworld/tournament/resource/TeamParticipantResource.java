package com.gamingworld.app.gamingworld.tournament.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamParticipantResource {
    private Long id;
    private Long participantProfileId;
    private Long tournamentId;
    private Long teamId;
}
