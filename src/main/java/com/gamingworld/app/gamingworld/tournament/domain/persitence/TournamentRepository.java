package com.gamingworld.app.gamingworld.tournament.domain.persitence;

import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query(value = "SELECT t FROM Tournament t WHERE t.user.id = :tournamentId")
    public List<Tournament> findByUserId (@Param("tournamentId") Long tournamentId);


}
