package com.gamingworld.app.gamingworld.tournament.resource;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateParticipantResource {

    @NotNull
    private Long participantProfileId;

    @NotNull
    private Long tournamentId;

    @NotNull
    private Number points;
}