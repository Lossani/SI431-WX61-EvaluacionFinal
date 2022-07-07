package com.gamingworld.app.gamingworld.tournament.resource;


import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class CreateTournamentResource {
    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private Number prizePool;

    @NotNull
    private Boolean isTeamMode;

    @Nullable
    private String urlToImage;

    @NotNull
    private Date tournamentDate;

    @NotNull
    private Boolean tournamentStatus;

    @NotNull
    private Integer tournamentCapacity;

    @NotNull
    private Integer gameId;




}
