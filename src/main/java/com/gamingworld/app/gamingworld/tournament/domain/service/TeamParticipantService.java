package com.gamingworld.app.gamingworld.tournament.domain.service;

import com.gamingworld.app.gamingworld.tournament.domain.model.entity.TeamParticipant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamParticipantService {
    public List<TeamParticipant> getAll();

    public TeamParticipant getById(Long participantId);

    public TeamParticipant getByTournamentId(Long tournamentId);
    public List<TeamParticipant> getAllByTeamId(Long teamId);

    public TeamParticipant create(Long tournamentId, Long teamId,TeamParticipant teamParticipant);

    public TeamParticipant update(Long teamParticipantId, TeamParticipant teamParticipant);

    public ResponseEntity<?> delete(Long teamParticipantId);
}
