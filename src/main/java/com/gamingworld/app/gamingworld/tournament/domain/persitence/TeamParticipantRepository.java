package com.gamingworld.app.gamingworld.tournament.domain.persitence;

import com.gamingworld.app.gamingworld.tournament.domain.model.entity.TeamParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamParticipantRepository extends JpaRepository<TeamParticipant, Long> {

//    @Query(value = "SELECT * FROM tournament t WHERE t.user_id = ?1", nativeQuery = true)
//    public List<Tournament> findByUserId (Long tournamentId);

    @Query(value = "SELECT * FROM team_participant tp WHERE tp.team_id = ?1", nativeQuery = true)
    public List<TeamParticipant> findByTournamentId (Long teamId);
    
}
