package com.gamingworld.app.gamingworld.tournament.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateTeamResource {

    @NotNull
    private String name;

    private String description;

    private String imageUrl;

    @NotNull
    private String points;

    @NotNull
    private Long ownerProfileId;

    @NotNull
    private Long tournamentId;
}