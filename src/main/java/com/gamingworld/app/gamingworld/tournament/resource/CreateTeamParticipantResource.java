package com.gamingworld.app.gamingworld.tournament.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateTeamParticipantResource {

    @NotNull
    private Long participantProfileId;

    @NotNull
    private Long tournamentId;

    @NotNull
    private Long teamId;
}