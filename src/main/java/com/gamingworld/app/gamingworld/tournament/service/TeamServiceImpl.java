package com.gamingworld.app.gamingworld.tournament.service;

import com.gamingworld.app.gamingworld.security.domain.persistence.UserRepository;
import com.gamingworld.app.gamingworld.shared.exception.ResourceNotFoundException;
import com.gamingworld.app.gamingworld.shared.exception.ResourceValidationException;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Team;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.TeamRepository;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.TournamentRepository;
import com.gamingworld.app.gamingworld.tournament.domain.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String ENTITY = "Tournament";

    private final TeamRepository teamRepository;

    private final TournamentRepository tournamentRepository;

    private final Validator validator;

    public TeamServiceImpl(TeamRepository teamRepository, TournamentRepository tournamentRepository, Validator validator, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.validator = validator;
    }

    @Override
    public List<Team> getAll() { return teamRepository.findAll(); }

    @Override
    public Team getById(Long teamId) {
        return null;
    }

    @Override
    public List<Team> getAllByTournamentId(Long tournamentId) {
        return teamRepository.findByTournamentId(tournamentId);
    }

    @Override
    public Team create(Long tournamentId, Team team){
        if(tournamentRepository.findById(tournamentId).isEmpty())
            throw new ResourceNotFoundException(ENTITY, tournamentId);

        team.setTournamentId(tournamentId);

        Set<ConstraintViolation<Team>> violations = validator.validate(team);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        
        return teamRepository.save(team);
    }

    @Override
    public Team update(Long tournamentId, Team team) { return null; }

    @Override
    public ResponseEntity<?> delete(Long teamId) {
        return teamRepository.findById(teamId).map(team -> {
            teamRepository.delete(team);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, teamId));
    }

    @Override
    public List<Team> getTournamentsByUserId(Long userId) {
        return null;
    }
}
