package com.gamingworld.app.gamingworld.tournament.domain.service;

import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Participant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParticipantService {
    public List<Participant> getAll();

    public Participant getById(Long participantId);

    public Participant getByTournamentId(Long tournamentId);
    public List<Participant> getAllByTournamentId(Long tournamentId);

    public Participant create(Long tournamentId,Participant participant);

    public Participant update(Long tournamentId, Participant participant);

    public ResponseEntity<?> delete(Long tournamentId);
    public List<Participant> getTournamentsByUserId(Long userId);

    public Boolean validateParticipantInTournament(Long tournamentId, Long participantId);
}
