package com.gamingworld.app.gamingworld.tournament.service;

import com.gamingworld.app.gamingworld.security.domain.persistence.UserRepository;
import com.gamingworld.app.gamingworld.shared.exception.ResourceNotFoundException;
import com.gamingworld.app.gamingworld.shared.exception.ResourceValidationException;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Participant;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.ParticipantRepository;
import com.gamingworld.app.gamingworld.tournament.domain.persitence.TournamentRepository;
import com.gamingworld.app.gamingworld.tournament.domain.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ParticipantServiceImpl implements ParticipantService {


    private static final String ENTITY = "Tournament";

    private final ParticipantRepository participantRepository;

    private final TournamentRepository tournamentRepository;


    private final Validator validator;

    private final UserRepository userRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, TournamentRepository tournamentRepository, Validator validator, UserRepository userRepository) {
        this.participantRepository = participantRepository;
        this.tournamentRepository = tournamentRepository;
        this.validator = validator;
        this.userRepository = userRepository;
    }


    @Override
    public List<Participant> getAll() {
        return participantRepository.findAll();
    }

    @Override
    public Participant getById(Long participantId) {
        return null;
    }

    @Override
    public Participant getByTournamentId(Long tournamentId) {
        return null;
    }

    @Override
    public List<Participant> getAllByTournamentId(Long tournamentId) {
        return participantRepository.findByTournamentId(tournamentId);
    }

    @Override
    public Participant create(Long tournamentId, Participant participant) {

        if(tournamentRepository.findById(tournamentId).isEmpty())
            throw new ResourceNotFoundException(ENTITY, tournamentId);

        if (getAllByTournamentId(tournamentId).stream().anyMatch(o->o.getParticipantProfile().getId().equals(participant.getParticipantProfile().getId())))
            throw new ResourceNotFoundException("This participant is already registered in this tournament.");

        participant.setTournamentId(tournamentId);

        Set<ConstraintViolation<Participant>> violations = validator.validate(participant);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return participantRepository.save(participant);
    }

    @Override
    public Participant update(Long participantId, Participant request) {
        Set<ConstraintViolation<Participant>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return participantRepository.findById(participantId).map(participant ->
                participantRepository.save(
                        participant.withParticipantProfile(request.getParticipantProfile())
                                .withPoints(request.getPoints()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, participantId));
    }

    @Override
    public ResponseEntity<?> delete(Long tournamentId) {
        return participantRepository.findById(tournamentId).map(participant -> {
            participantRepository.delete(participant);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, tournamentId));
    }

    @Override
    public List<Participant> getTournamentsByUserId(Long userId) {
        return null;
    }

    @Override
    public Boolean validateParticipantInTournament(Long tournamentId, Long participantId) {
//        Participant participant = participantRepository.getById(participantId);
        return getAllByTournamentId(tournamentId).stream().noneMatch(o->o.getParticipantProfile().getId().equals(participantId)) && getAllByTournamentId(tournamentId).size() < tournamentRepository.getById(tournamentId).getTournamentCapacity();
    }


}
