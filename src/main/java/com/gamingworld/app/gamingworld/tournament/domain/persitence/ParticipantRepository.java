package com.gamingworld.app.gamingworld.tournament.domain.persitence;

import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query(value = "SELECT * FROM participant p WHERE p.tournament_id = ?1", nativeQuery = true)
    public List<Participant> findByTournamentId (Long tournamentId);

    @Query(value = "SELECT * FROM participant p WHERE p.participant_profile_id IN (SELECT id FROM profiles WHERE user_id = ?1) LIMIT 1", nativeQuery = true)
    public Optional<Participant> findByUserId (Long userId);
}
