package com.gamingworld.app.gamingworld.tournament.resource;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TournamentResource {

    private Long id;
    private String title;
    private String description;
    private Number prizePool;
    private Boolean isTeamMode;
    private String urlToImage;
    private Date tournamentDate;
    private Boolean tournamentStatus;
    private Integer tournamentCapacity;
    private Integer gameId;

}
