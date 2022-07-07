package com.gamingworld.app.gamingworld.tournament.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateTournamentResource {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private Number prizePool;

    @NotNull
    private String tHour;

    @NotNull
    private String tDate;

    @NotNull
    @Size(max = 1)
    private Integer tStatus;



}
