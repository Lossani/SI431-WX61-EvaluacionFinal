package com.gamingworld.app.gamingworld.tournament.domain.service;

import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamService {

    public List<Team> getAll();

    public Team getById(Long teamId);

    public List<Team> getAllByTournamentId(Long tournamentId);

    public Team create(Long tournamentId, Team team);

    public Team update(Long tournamentId, Team team);

    public ResponseEntity<?> delete(Long teamId);

    public List<Team> getTournamentsByUserId(Long userId);

}
