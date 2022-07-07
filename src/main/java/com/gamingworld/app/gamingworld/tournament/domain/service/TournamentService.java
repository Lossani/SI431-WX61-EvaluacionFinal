package com.gamingworld.app.gamingworld.tournament.domain.service;


import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Participant;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Team;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Tournament;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TournamentService {
    public List<Tournament> getAll();
    public Tournament getById(Long tournamentId);
    public Tournament create(Long userId, Tournament tournament);
    public Tournament update(Long tournamentId, Tournament tournament);
    public ResponseEntity<?> delete(Long tournamentId);
    public List<Tournament> getTournamentsByUserId(Long userId);
    public Participant updateParticipantPoints(Long tournamentId, Long participantId, int points);
    public Team updateTeamPoints(Long tournamentId, Long teamId, int points);
    public ResponseEntity<?> deleteParticipantInTournament(Long tournamentId, Long userId);
    public Tournament endTournament(Long tournamentId);
}
