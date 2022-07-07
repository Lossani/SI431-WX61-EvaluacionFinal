package com.gamingworld.app.gamingworld.tournament.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResource {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer points;
    private Long ownerProfileId;
    private Long tournamentId;
}