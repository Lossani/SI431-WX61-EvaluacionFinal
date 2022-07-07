package com.gamingworld.app.gamingworld.tournament.service;

import com.gamingworld.app.gamingworld.security.domain.persistence.UserRepository;
import com.gamingworld.app.gamingworld.shared.exception.ResourceNotFoundException;
import com.gamingworld.app.gamingworld.shared.exception.ResourceValidationException;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.TeamParticipant;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.TeamParticipantRepository;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.TournamentRepository;
import com.gamingworld.app.gamingworld.tournament.domain.service.TeamParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class TeamParticipantServiceImpl implements TeamParticipantService {
    private static final String ENTITY = "TeamParticipant";


    private final TournamentRepository tournamentRepository;

    private final TeamParticipantRepository teamParticipantRepository;

    private final Validator validator;


    public TeamParticipantServiceImpl(TeamParticipantRepository teamParticipantRepository, TournamentRepository tournamentRepository, Validator validator, UserRepository userRepository) {
        this.teamParticipantRepository = teamParticipantRepository;
        this.tournamentRepository = tournamentRepository;
        this.validator = validator;
    }

    @Override
    public List<TeamParticipant> getAll() {
        return teamParticipantRepository.findAll();
    }

    @Override
    public TeamParticipant getById(Long participantId) {
        return teamParticipantRepository.getById(participantId);
    }

    @Override
    public TeamParticipant getByTournamentId(Long tournamentId) {
        return null;
    }

    @Override
    public List<TeamParticipant> getAllByTeamId(Long teamId) {
        return teamParticipantRepository.findByTournamentId(teamId);
    }

    @Override
    public TeamParticipant create(Long tournamentId, Long teamId, TeamParticipant teamParticipant) {
        if(tournamentRepository.findById(tournamentId).isEmpty())
            throw new ResourceNotFoundException(ENTITY, tournamentId);

        teamParticipant.setTournamentId(tournamentId);

        Set<ConstraintViolation<TeamParticipant>> violations = validator.validate(teamParticipant);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return teamParticipantRepository.save(teamParticipant);
    }

    @Override
    public TeamParticipant update(Long teamParticipantId, TeamParticipant request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long teamParticipantId) {
        return teamParticipantRepository.findById(teamParticipantId).map(participant -> {
            teamParticipantRepository.delete(participant);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, teamParticipantId));
    }
}
