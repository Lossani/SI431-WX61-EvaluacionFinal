package com.gamingworld.app.gamingworld.tournament.service;

import com.gamingworld.app.gamingworld.security.domain.model.entity.User;
import com.gamingworld.app.gamingworld.security.domain.persistence.UserRepository;
import com.gamingworld.app.gamingworld.shared.exception.ResourceNotFoundException;
import com.gamingworld.app.gamingworld.shared.exception.ResourceValidationException;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Participant;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Team;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Tournament;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.ParticipantRepository;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.TeamRepository;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.TournamentRepository;
import com.gamingworld.app.gamingworld.tournament.domain.service.TournamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@Service
public class TournamentServiceImpl implements TournamentService {

    private static final String ENTITY = "Tournament";

    private final TournamentRepository tournamentRepository;

    private final Validator validator;

    private final UserRepository userRepository;

    private final ParticipantRepository participantRepository;

    private final TeamRepository teamRepository;


    public TournamentServiceImpl(TournamentRepository tournamentRepository, Validator validator, UserRepository userRepository, ParticipantRepository participantRepository, TeamRepository teamRepository) {
        this.tournamentRepository = tournamentRepository;
        this.validator = validator;
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public Tournament getById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, tournamentId));
    }

    @Override
    public Tournament create(Long userId, Tournament tournament) {
        Set<ConstraintViolation<Tournament>> violations = validator.validate(tournament);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        User tournamentManager = userRepository.getById(userId);
        tournament.setUser(tournamentManager);
        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament update(Long tournamentId, Tournament request) {

        Set<ConstraintViolation<Tournament>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return tournamentRepository.findById(tournamentId).map(tournament ->
                tournamentRepository.save(
                        tournament.withTitle(request.getTitle())
                                .withDescription(request.getDescription())
                                .withPrizePool(request.getPrizePool())
                                )
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, tournamentId));
    }

    @Override
    public ResponseEntity<?> delete(Long tournamentId) {
        return tournamentRepository.findById(tournamentId).map(tournament -> {
            tournamentRepository.delete(tournament);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, tournamentId));
    }

    @Override
    public List<Tournament> getTournamentsByUserId(Long userId) {
        return tournamentRepository.findByUserId(userId);
    }

    @Override
    public Participant updateParticipantPoints(Long tournamentId, Long participantId, int points) {

        Participant participant = participantRepository.getById(participantId);
        participant.setPoints(points);

        return participantRepository.save(participant);
    }

    @Override
    public Team updateTeamPoints(Long tournamentId, Long teamId, int points) {
        Team team = teamRepository.getById(teamId);
        team.setPoints(points);

        return teamRepository.save(team);
    }

    @Override
    public ResponseEntity<?> deleteParticipantInTournament(Long tournamentId, Long userId) {

        return participantRepository.findByUserId(userId).map(participant -> {
            participantRepository.delete(participant);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));

    }

    @Override
    public Tournament endTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.getById(tournamentId);
        tournament.setTournamentStatus(false);
        return tournamentRepository.save(tournament);
    }
}
